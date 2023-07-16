/**
 * 
 */
package com.sudhanshu.learnkafka.producer;

import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author SudhanshuS
 *
 */
@Configuration
public class Topic1Producer {

	private final Logger log = LoggerFactory.getLogger(Topic1Producer.class);

	@Value("${learnkafka.topic1.name}")
	private String topicName;

	KafkaTemplate<Integer, String> kafkaTemplate;

	public Topic1Producer(KafkaTemplate<Integer, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public ListenableFuture<SendResult<Integer, String>> sendMessage(Integer key, String value) {

		ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value, topicName);

		ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
			@Override
			public void onFailure(Throwable ex) {
				log.warn("Failure Callback: Unable to deliver message [{}]. {}", value, ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<Integer, String> result) {

				
				log.info("Success Callback for key : {} and value is [{}] , "
						+ "partition is {} and offset is {}", key, value, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
				
			}
		});
		
		return listenableFuture;
	}

	private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {
		List<Header> recordHeaders = List.of(new RecordHeader("sample-header", "sudhanshu".getBytes()));
		return new ProducerRecord<>(topic, null, key, value, recordHeaders);
	}

}
