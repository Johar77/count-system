package com.johar.countingservice.infrastructure.common.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.johar.countingservice.domain.countview.entity.CountPerMinute;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

/**
 * @ClassName: KafkaProducer
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-03 00:14
 * @Since: 1.0.0
 */
@Service
@Slf4j
public class KafkaProducer {

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publish(List<CountPerMinute> messages)  {
        String message = null;
        try {
            message = JacksonUtils.enhancedObjectMapper().writeValueAsString(messages);
        } catch (JsonProcessingException e) {
            log.error("序列化失败", e);
        }
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topicName, message);
        result.addCallback(
                success -> {
                    log.info("Kafka send message success: {}", success.toString());
                },
                failed -> {
                    log.error("Kafka send message failed: {}", failed.getMessage());
                }
        );
    }
}
