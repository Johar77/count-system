package com.johar.countingservice.domain.countview.entity;

import lombok.Getter;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: CountPerMinute
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 15:16
 * @Since: 1.0.0
 */
public class CountPerMinute {

    private final Integer ZERO = 0;

    /**
     * 格式：yyyy-MM-dd HH:mm:00，例如：2020-08-30 16：30：00
     */
    @Getter
    private final Date countTime;

    @Getter
    private final int videoId;

    @Getter
    private final ConcurrentHashMap<EventType, Integer> counts;

    public CountPerMinute(Date countTime, int videoId) {
        this.countTime = countTime;
        this.videoId = videoId;
        this.counts = new ConcurrentHashMap<>(EventType.values().length);
        initMap();
    }

    public CountPerMinute(Date countTime, int videoId, EventType eventType, Integer count) {
        this.countTime = countTime;
        this.videoId = videoId;
        this.counts = new ConcurrentHashMap<>(EventType.values().length);
        initMap();
        this.counts.put(eventType, count);
    }

    public synchronized CountPerMinute add(EventType eventType, Integer count){
        Integer initialValue = this.counts.getOrDefault(eventType, ZERO);
        this.counts.put(eventType, initialValue + count);
        return this;
    }

    public Integer getEventCount(EventType type){
        return this.counts.getOrDefault(type, ZERO);
    }

    private void initMap(){
        for(EventType type : EventType.values()){
            this.counts.put(type, ZERO);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CountPerMinute that = (CountPerMinute) o;
        return videoId == that.videoId &&
                countTime.equals(that.countTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countTime, videoId);
    }
}
