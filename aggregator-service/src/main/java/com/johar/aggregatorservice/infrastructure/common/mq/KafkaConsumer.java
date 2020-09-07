package com.johar.aggregatorservice.infrastructure.common.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johar.aggregatorservice.domain.countview.service.CountViewDomainService;
import com.johar.aggregatorservice.interfaces.dto.CountPerMinute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: KafkaConsumer
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-06 21:57
 * @Since: 1.0.0
 */
@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    private CountViewDomainService countViewDomainService;

    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return JacksonUtils.enhancedObjectMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    @KafkaListener(topics = "count.view.topic")
    public void onReceiveMessage(String message){
        try {
            List<CountPerMinute> result = JacksonUtils.enhancedObjectMapper().readValue(message, getCollectionType(List.class, CountPerMinute.class));
            countViewDomainService.save(result);
        } catch (JsonProcessingException e) {
            log.error("KafkaConsumer Message: {}, {}", message, e);
        }
    }
}
