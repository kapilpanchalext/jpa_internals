package com.java.producer.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.java.producer.event.ProductCreatedEvent;
import com.java.producer.model.CreateProductRestModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

	@Override
	public String createProduct(CreateProductRestModel productRestModel) throws Exception {
		String productId = UUID.randomUUID().toString();

		// TODO: Persist Product Details into Database before publishing an Event
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
				productId, 
				productRestModel.getTitle(), 
				productRestModel.getPrice(), 
				productRestModel.getQuantity());

		// Asynchronous Message Send
//		CompletableFuture<SendResult<String, ProductCreatedEvent>> future = 
//				kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);
//		
//		future.whenComplete((result, exception) -> {
//			if(exception != null) {
//				LOGGER.error("*****Failed to send Error Message: " + exception.getMessage());
//			} else {
//				LOGGER.info("*****Message was sent Successfully: " + result.getRecordMetadata());
//			}
//		});

		// Synchronous Message Send
		LOGGER.info("*****Before publishing product created event: " + productId);
		SendResult<String, ProductCreatedEvent> result = 
				kafkaTemplate
					.send("product-created-events-topic", productId, productCreatedEvent)
					.get();
		
		LOGGER.info("Partition: " + result.getRecordMetadata().partition());
		LOGGER.info("Topic: " + result.getRecordMetadata().topic());
		LOGGER.info("Offset: " + result.getRecordMetadata().offset());
		LOGGER.info("Key: " + result.getProducerRecord().key());
		LOGGER.info("Value: " + result.getProducerRecord().value().toString());
		LOGGER.info("Headers: " + result.getProducerRecord().headers());
		LOGGER.info("*****Returning product id: " + productId);

//		LOGGER.info("*****Before publishing product created event: " + productId);
//		SendResult<String, ProductCreatedEvent> result = 
//				kafkaTemplate.send("topic2", productId, productCreatedEvent).get();
//
//		LOGGER.info("Partition: " + result.getRecordMetadata().partition());
//		LOGGER.info("Topic: " + result.getRecordMetadata().topic());
//		LOGGER.info("Offset: " + result.getRecordMetadata().offset());
//		LOGGER.info("*****Returning product id: " + productId);

		return productId;
	}
}