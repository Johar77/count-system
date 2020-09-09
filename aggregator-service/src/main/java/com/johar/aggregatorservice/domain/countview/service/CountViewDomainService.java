package com.johar.aggregatorservice.domain.countview.service;

import com.johar.aggregatorservice.domain.countview.entity.EventType;
import com.johar.aggregatorservice.domain.countview.repository.facade.CountViewRepositoryInterface;
import com.johar.aggregatorservice.domain.countview.repository.mapper.VideoStatsDao;
import com.johar.aggregatorservice.domain.countview.repository.persistence.CountViewRepositoryImpl;
import com.johar.aggregatorservice.domain.countview.repository.po.VideoInfoPo;
import com.johar.aggregatorservice.domain.countview.repository.po.VideoStatsPo;
import com.johar.aggregatorservice.interfaces.dto.CountPerMinute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CountViewDomainService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-08 00:06
 * @Since: 1.0.0
 */
@Service
public class CountViewDomainService {
    private final int NUM_BATCH_SIZE = 1000;

    @Autowired
    private CountViewRepositoryInterface countViewRepositoryInterface;

    public void save(List<CountPerMinute> countPerMinutes){
        List<VideoStatsPo> poList = new ArrayList<>();
        countPerMinutes.stream().forEach(countPerMinute -> {
            for(Map.Entry<EventType, Integer> entry : countPerMinute.getCounts().entrySet()){
                if (entry.getValue() <= 0){
                    continue;
                }
                VideoStatsPo po = new VideoStatsPo();
                po.setCount(entry.getValue());
                po.setEventType(entry.getKey());
                po.setVideoInfoId(countPerMinute.getVideoId());
                po.setTimestamp(countPerMinute.getCountTime().getTime());
                poList.add(po);
                if (poList.size() >= NUM_BATCH_SIZE){
                    countViewRepositoryInterface.save(poList);
                    poList.clear();
                }
            }
        });

        if (poList.size() > 0){
            countViewRepositoryInterface.save(poList);
        }
    }


}
