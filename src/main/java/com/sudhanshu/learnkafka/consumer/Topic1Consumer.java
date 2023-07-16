package com.sudhanshu.learnkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Topic1Consumer {

	private final Logger log = LoggerFactory.getLogger(Topic1Consumer.class);

	@KafkaListener(topics = {
			"${learnkafka.topic1.name}" }, groupId = "my-consumer-listener-group", autoStartup = "true")
	public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {

		log.info("Received consumer record {}", consumerRecord);

	}

}