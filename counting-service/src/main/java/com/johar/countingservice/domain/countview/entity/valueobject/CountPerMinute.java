package com.johar.countingservice.domain.countview.entity.valueobject;

import com.johar.countingservice.domain.countview.entity.EventType;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassName: CountPerMinute
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 15:16
 * @Since: 1.0.0
 */
@Data
public class CountPerMinute {

    /**
     * 格式：yyyy-MM-dd HH:mm:00，例如：2020-08-30 16：30：00
     */
    private Date countTime;

    private EventType eventType;

    private int count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountPerMinute that = (CountPerMinute) o;
        return countTime.equals(that.countTime) &&
                eventType == that.eventType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countTime, eventType);
    }
}
