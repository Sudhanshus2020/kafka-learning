/**
 * 
 */
package com.sudhanshu.learnkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author SudhanshuS
 *
 */
@Configuration
public class TopicConfig {

	@Value("${learnkafka.topic1.name}")
	private String topic1Name;

	@Bean
	NewTopic createTopic1() {
		return TopicBuilder.name(topic1Name).partitions(3).replicas(1).build();
	}

}