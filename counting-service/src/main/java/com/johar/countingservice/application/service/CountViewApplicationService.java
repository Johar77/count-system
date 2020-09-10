package com.johar.countingservice.application.service;

import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.entity.EventType;
import com.johar.countingservice.domain.countview.service.CountViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: CountViewApplicationService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-01 08:35
 * @Since: 1.0.0
 */
@Service
@Slf4j
public class CountViewApplicationService {

    private final Date DEFAULT_MIN_TIME = new Calendar.Builder()
            .setDate(2020, 1, 1)
            .build().getTime();

    @Autowired
    private CountViewService countViewService;

    public void addCountViewEvent(@NotNull CountViewInfo countViewInfo){
        countViewService.addCountViewEvent(countViewInfo);
    }

    public CountViewInfo findViewsCount(long videoId, Date startTime, Date endTime){
        return countViewService.findCountViewInfo(videoId, EventType.VIEW, startTime, endTime);
    }

    public CountViewInfo findEventCount(long videoId, EventType eventType, Date startTime, Date endTime){
        return countViewService.findCountViewInfo(videoId, eventType, startTime, endTime);
    }

    public CountViewInfo findEventCurrentCount(long videoId, EventType eventType){
        return countViewService.findCountViewInfo(videoId, eventType, DEFAULT_MIN_TIME, new Date());
    }
}
