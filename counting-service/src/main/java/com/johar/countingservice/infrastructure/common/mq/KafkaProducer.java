package com.johar.countingservice.infrastructure.common.mq;

import com.johar.countingservice.domain.countview.entity.CountPerMinute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
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
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(List<CountPerMinute> messages){
        ListenableFuture<SendResult<String, Object>> result = kafkaTemplate.send(topicName, messages);
        result.addCallback(
                success -> {
                    log.debug("Kafka send message success: {}", success);
                },
                failed -> {
                    log.error("Kafka send message failed: {}", failed.getMessage());
                }
        );
    }
}
