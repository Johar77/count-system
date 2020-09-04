package com.johar.countingservice.domain.countview.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.johar.countingservice.domain.countview.entity.CountPerMinute;
import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.entity.EventType;
import com.johar.countingservice.infrastructure.common.mq.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

//    @Value("${count.view.time}")
//    private int internalTime;
//
//    @Value("${count.view.cache.initial.capacity}")
//    private int initialCapacity;

    @Autowired
    private KafkaProducer kafkaProducer;

    private final Cache<Date, List<CountPerMinute>> countViewCache;

    public CountViewService(@Value("${count.view.time}") int internalTime, @Value("${count.view.cache.initial.capacity}") int initialCapacity) {

        RemovalListener<Date, List<CountPerMinute>> removalListener = new RemovalListener<Date, List<CountPerMinute>>() {
            @Override
            public void onRemoval(RemovalNotification<Date, List<CountPerMinute>> notification) {
                kafkaProducer.publish(notification.getValue());
                // 批量发送到kafka
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                log.info("{} -> {} : {} : {}", df.format(System.currentTimeMillis()), df.format(notification.getKey()));
            }
        };
        this.countViewCache = CacheBuilder
                .newBuilder()
                .initialCapacity(initialCapacity)
                .expireAfterWrite(internalTime, TimeUnit.SECONDS)
                .removalListener(removalListener)
                .build();
    }

    public synchronized void addCountViewEvent(@NotNull CountViewInfo countViewInfo){
        CountPerMinute newValue = toCountPerMinute(countViewInfo);
        List<CountPerMinute> countPerMinutes = countViewCache.getIfPresent(countViewInfo.getCountTime());
        if (countPerMinutes == null){
            List<CountPerMinute> newCollection = new ArrayList<>();
            newCollection.add(newValue);
            countViewCache.put(countViewInfo.getCountTime(), newCollection);
            return;
        }

        if (countPerMinutes.contains(newValue)){
            countPerMinutes.stream().filter(countPerMinute -> countPerMinute.getVideoId() == newValue.getVideoId())
                    .map(countPerMinute -> countPerMinute.add(countViewInfo.getEventType(), countViewInfo.getCount()))
                    .forEach(countPerMinute -> log.info("{}-{}-{}", countPerMinute.getCountTime(), countPerMinute.getVideoId(), countPerMinute.getEventCount(countViewInfo.getEventType())));
        } else {
            countPerMinutes.add(newValue);
        }
    }

    private CountPerMinute toCountPerMinute(@NotNull CountViewInfo countViewInfo){
        CountPerMinute countPerMinute = new CountPerMinute(countViewInfo.getCountTime(), countViewInfo.getVideoInfo().getId(), countViewInfo.getEventType(), countViewInfo.getCount());
        return countPerMinute;
    }
}
