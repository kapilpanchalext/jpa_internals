package com.java.email.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.java.producer.event.ProductCreatedEvent;

@Component
@KafkaListener(topics = {"product-created-events-topic"})
public class ProductCreatedEventHandler {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@KafkaHandler
	public void handle(ProductCreatedEvent productCreatedEvent) {
		String BLUE = "\033[34m";
		String RESET = "\033[0m";
		LOGGER.info(BLUE + "Received a New Event: " + productCreatedEvent + RESET);
	}
}