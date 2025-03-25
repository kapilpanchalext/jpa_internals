package com.java.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ApacheKafkaListener {
	@KafkaListener(id = "myId", topics = "helloworld-topic1")
	public void listen(String topicString) {
		System.err.println("Kafka Listener: " + topicString);
	}
}