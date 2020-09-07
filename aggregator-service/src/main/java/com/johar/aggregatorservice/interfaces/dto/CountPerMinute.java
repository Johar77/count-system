package com.johar.aggregatorservice.interfaces.dto;

import com.johar.aggregatorservice.domain.countview.entity.EventType;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: CountPerMinute
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-08 00:11
 * @Since: 1.0.0
 */
@Data
public class CountPerMinute {
    /**
     * 格式：yyyy-MM-dd HH:mm:00，例如：2020-08-30 16：30：00
     */

    private Date countTime;

    private int videoId;

    private ConcurrentHashMap<EventType, Integer> counts;
}
