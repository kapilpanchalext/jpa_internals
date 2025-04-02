package com.java.email.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.java.email.entity.ProcessedEventEntity;
import com.java.email.exception.NotRetryableException;
import com.java.email.exception.RetryableException;
import com.java.email.repository.ProcessedEventRepository;
import com.java.producer.event.ProductCreatedEvent;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@KafkaListener(topics = {"product-created-events-topic"})
@RequiredArgsConstructor
@Transactional
public class ProductCreatedEventHandler {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final RestTemplate restTemplate;
	private final ProcessedEventRepository processedEventRepository;
	
	@KafkaHandler
	public void handle(@Payload ProductCreatedEvent productCreatedEvent, 
			@Header(value = "messageId") String messageId, 
			@Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {
		String BLUE = "\033[34m";
		String RESET = "\033[0m";
		LOGGER.info(BLUE + "Received a New Event: " + productCreatedEvent + RESET);
		
		//Check if this message was already processed before
		ProcessedEventEntity existingRecord = processedEventRepository.findByMessageId(messageId);
		
		if(existingRecord != null) {
			LOGGER.info("Found a duplicate message Id");
			return;
		}
		
//		if(true) {
//			throw new NotRetryableException("An exception occured. No need to consume this message again.");
//		}

		String requestUrl = "http://localhost:8082/response/200";

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
		// Save a unique message Id in a message table
		
		try {
			processedEventRepository.save(ProcessedEventEntity.builder()
					.messageId(messageId)
					.productId(productCreatedEvent.getProductId())
					.build());
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			throw new NotRetryableException(e);
		}
	}
}