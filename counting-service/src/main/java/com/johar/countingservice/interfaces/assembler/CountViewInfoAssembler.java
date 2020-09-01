package com.johar.countingservice.interfaces.assembler;

import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.entity.EventType;

import java.util.Date;

/**
 * @ClassName: CountViewInfoAssembler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-01 08:38
 * @Since: 1.0.0
 */
public class CountViewInfoAssembler {

    public static CountViewInfo toDo(Date countTime, int videoId, EventType eventType, int count){

        CountViewInfo countViewInfo = CountViewInfo
                                            .builder()
                                            .countTime(countTime)
                                            .eventType(eventType)
                                            .count(count)
                                            .build();
        return countViewInfo;
    }
}
