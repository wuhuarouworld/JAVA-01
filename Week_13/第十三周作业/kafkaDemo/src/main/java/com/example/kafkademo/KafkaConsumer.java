package com.example.kafkademo;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = {"test1"})
    public void consumeMessage(ConsumerRecord<?,?> record) {
        log.info("topic:{}, partition:{}, content:{}",record.topic(),record.partition(),record.value());
    }
}
