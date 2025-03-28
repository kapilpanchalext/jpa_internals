package com.java.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ApacheKafkaListener {

//	@KafkaListener(id = "myId", topics = "helloworld-topic_1")
//	public void listen(String topicString) {
//		System.err.println("Kafka Listener: " + topicString);
//	}
	
    @KafkaListener(topics = "helloworld-topic_1", id = "myId")
    public void listen(ConsumerRecord<String, String> record) {
        if(record.key().equalsIgnoreCase("helloworld")) {
        	System.err.println("Received message:");
            System.err.println(record.key() + ":" + record.value());
        } else {
        	System.err.println("Some other key Value Pair:");
        	System.err.println(record.key() + ":" + record.value());
        }
    }
}