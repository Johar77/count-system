package com.johar.countingservice.domain.countview.service;


import com.johar.countingservice.domain.countview.entity.CountPerMinute;
import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.entity.EventType;
import com.johar.countingservice.domain.countview.repository.facade.CountViewInterface;
import com.johar.countingservice.infrastructure.common.mq.KafkaProducer;
import com.johar.countingservice.infrastructure.util.CountTimeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: CountViewService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-01 00:05
 * @Since: 1.0.0
 */
@Service
@Slf4j
public class CountViewService {

    private KafkaProducer kafkaProducer;

    private CountViewInterface countViewInterface;

    private final ConcurrentHashMap<Date, List<CountPerMinute>> countViewCache;

    public CountViewService(@Value("${count.view.cache.initial.capacity}") int initialCapacity, KafkaProducer kafkaProducer, CountViewInterface countViewInterface) {
        this.kafkaProducer = kafkaProducer;
        this.countViewCache = new ConcurrentHashMap<>(initialCapacity);
        this.countViewInterface = countViewInterface;
    }

    @Scheduled(cron = "1/59 * * * * ?")
    public void timingPushMessage(){
        try {
            Date currentTime = CountTimeGenerator.currentTime();
            countViewCache.entrySet().stream()
                    .filter(dateListEntry -> !dateListEntry.getKey().equals(currentTime))
                    .forEach(dateListEntry -> {
                        log.info("push currentTime: {}, countTime: {}", currentTime, dateListEntry.getKey());
                        countViewCache.remove(dateListEntry.getKey());
                        kafkaProducer.publish(dateListEntry.getValue());
                    });
        } catch (Exception e){
            log.error("timing push message to Kafka error:", e);
        }
    }

    public synchronized void addCountViewEvent(@NotNull CountViewInfo countViewInfo){
        CountPerMinute newValue = toCountPerMinute(countViewInfo);
        List<CountPerMinute> countPerMinutes = countViewCache.getOrDefault(countViewInfo.getCountTime(), null);
        if (countPerMinutes == null){
            List<CountPerMinute> newCollection = new ArrayList<>();
            newCollection.add(newValue);
            countViewCache.put(countViewInfo.getCountTime(), newCollection);
            return;
        }

        if (countPerMinutes.contains(newValue)){
            countPerMinutes.stream().filter(countPerMinute -> countPerMinute.getVideoId() == newValue.getVideoId())
                    .map(countPerMinute -> countPerMinute.add(countViewInfo.getEventType(), (int)countViewInfo.getCount()))
                    .forEach(countPerMinute -> log.debug("{}-{}-{}-{}",
                            countPerMinute.getCountTime(),
                            countPerMinute.getVideoId(),
                            countViewInfo.getEventType(),
                            countPerMinute.getEventCount(countViewInfo.getEventType())));
        } else {
            countPerMinutes.add(newValue);
        }
    }

    public CountViewInfo findCountViewInfo(Long videoId, EventType eventType, @NotNull Date startTime, @NotNull Date endTime){
        return this.countViewInterface.findCountViewInfo(videoId, eventType, startTime, endTime);
    }

    private CountPerMinute toCountPerMinute(@NotNull CountViewInfo countViewInfo){
        CountPerMinute countPerMinute = new CountPerMinute(countViewInfo.getCountTime(), countViewInfo.getVideoInfo().getId(), countViewInfo.getEventType(), (int)countViewInfo.getCount());
        return countPerMinute;
    }
}
