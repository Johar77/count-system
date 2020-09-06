package com.johar.aggregatorservice.infrastructure.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

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

    @KafkaListener(topics = "count.view.topic")
    public void onReceiveMessage(String message){
        log.info("Receive Message: {}", message);
    }
}
