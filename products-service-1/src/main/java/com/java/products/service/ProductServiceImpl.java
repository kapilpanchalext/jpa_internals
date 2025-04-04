package com.java.products.service;

import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.products.event.ProductCreatedEvent;
import com.java.products.model.CreateProductRestModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
	private final Logger LOGGER  = LoggerFactory.getLogger(this.getClass());
	
	@Transactional(value = "transactionManager")
	@Override
	public String createProduct(CreateProductRestModel productRestModel) throws Exception {
		String productId = UUID.randomUUID().toString();
		
		// TODO: Persist Product Details into database table before publishing an Event
		
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,
				productRestModel.getTitle(), productRestModel.getPrice(), 
				productRestModel.getQuantity());
		
		LOGGER.info("Before publishing a ProductCreatedEvent");
		
		ProducerRecord<String, ProductCreatedEvent> record = new ProducerRecord<>(
				"product-created-events-topic",
				productId,
				productCreatedEvent);
		record.headers().add("messageId", UUID.randomUUID().toString().getBytes());
		
		SendResult<String, ProductCreatedEvent> result = 
				kafkaTemplate.send(record).get();
		
		LOGGER.info("Partition: " + result.getRecordMetadata().partition());
		LOGGER.info("Topic: " + result.getRecordMetadata().topic());
		LOGGER.info("Offset: " + result.getRecordMetadata().offset());
		
		LOGGER.info("***** Returning product id");
		
		return productId;
	}
}
