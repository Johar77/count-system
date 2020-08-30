package com.johar.countingservice.infrastructure.common.converter;

import com.johar.countingservice.domain.countview.entity.EventType;
import org.springframework.core.convert.converter.Converter;

/**
 * @ClassName: StringToEventTypeConverter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 17:03
 * @Since: 1.0.0
 */
public class StringToEventTypeConverter implements Converter<String, EventType> {
    @Override
    public EventType convert(String source) {
        return EventType.valueOf(source.toUpperCase());
    }
}
