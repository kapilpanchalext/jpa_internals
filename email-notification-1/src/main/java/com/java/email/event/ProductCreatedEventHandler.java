package com.java.email.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.java.email.exception.NotRetryableException;
import com.java.email.exception.RetryableException;
import com.java.producer.event.ProductCreatedEvent;

import lombok.RequiredArgsConstructor;

@Component
@KafkaListener(topics = {"product-created-events-topic"})
@RequiredArgsConstructor
public class ProductCreatedEventHandler {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final RestTemplate restTemplate;

	@KafkaHandler
	public void handle(ProductCreatedEvent productCreatedEvent) {
		String BLUE = "\033[34m";
		String RESET = "\033[0m";
		
//		if(true) {
//			throw new NotRetryableException("An exception occured. No need to consume this message again.");
//		}
		
		String requestUrl = "http://localhost:8082";
		
		try {
			ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
			if(response.getStatusCode().value() == HttpStatus.OK.value()) {
				LOGGER.info("Received response from a remote Service: " + response.getBody());
			}
		} catch (ResourceAccessException e) {
			LOGGER.error(e.getMessage());
			throw new RetryableException(e);
		} catch (HttpServerErrorException e) {
			LOGGER.error(e.getMessage());
			throw new NotRetryableException(e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new NotRetryableException(e);
		}
		
//		LOGGER.info(BLUE + "Received a New Event: " + productCreatedEvent + RESET);
	}
}