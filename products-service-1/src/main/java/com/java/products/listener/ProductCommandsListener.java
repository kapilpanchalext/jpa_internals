package com.java.products.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.java.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@KafkaListener(topics = "${products.commands.topic.name}")
@RequiredArgsConstructor
@Slf4j
public class ProductCommandsListener {

	private final ProductService productService;
	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${products.events.topic.name}")
	private String productEventsTopicName;

	@KafkaHandler
	public void handleCommand(@Payload ReserveProductCommand command) {

		try {
			Product desiredProduct = new Product(command.getProductId(), command.getProductQuantity());
			Product reservedProduct = productService.reserve(desiredProduct, command.getOrderId());
			ProductReservedEvent productReservedEvent = new ProductReservedEvent(command.getOrderId(),
					command.getProductId(), reservedProduct.getPrice(), command.getProductQuantity());
			kafkaTemplate.send(productEventsTopicName, productReservedEvent);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ProductReservationFailedEvent productReservationFailedEvent = new ProductReservationFailedEvent(
					command.getProductId(), command.getOrderId(), command.getProductQuantity());
			kafkaTemplate.send(productEventsTopicName, productReservationFailedEvent);
		}
	}
}
