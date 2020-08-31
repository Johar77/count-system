package com.johar.countingservice.domain.countview.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.johar.countingservice.domain.countview.entity.CountPerMinute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
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

    @Value("${count.view.time}")
    private int internalTime;

    @Value("${count.view.cache.initial.capacity}")
    private int initialCapacity;

    private final Cache<Date, List<CountPerMinute>> countViewCache;

    public CountViewService() {

        RemovalListener<Date, List<CountPerMinute>> removalListener = new RemovalListener<Date, List<CountPerMinute>>() {
            @Override
            public void onRemoval(RemovalNotification<Date, List<CountPerMinute>> notification) {
                // 批量发送到kafka
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                log.info("{} -> {}", df.format(System.currentTimeMillis()), df.format(notification.getKey()));
            }
        };
        this.countViewCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(internalTime, TimeUnit.SECONDS)
                .initialCapacity(initialCapacity)
                .removalListener(removalListener)
                .build();
    }


}
