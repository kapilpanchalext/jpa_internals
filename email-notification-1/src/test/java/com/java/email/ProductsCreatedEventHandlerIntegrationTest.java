package com.java.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.web.client.RestTemplate;

import com.java.email.entity.ProcessedEventEntity;
import com.java.email.event.ProductCreatedEventHandler;
import com.java.email.repository.ProcessedEventRepository;
import com.java.producer.event.ProductCreatedEvent;

@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}")
public class ProductsCreatedEventHandlerIntegrationTest {
	
	@MockBean
	ProcessedEventRepository processedEventRepository;
	
	@MockBean
	RestTemplate restTemplate;
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@SpyBean
	ProductCreatedEventHandler productCreatedEventHandler;

	@Test
	public void testProductCreatedEventHandler_OnProductCreated_HandlesEvent() throws InterruptedException, ExecutionException {
		
		// Given
		ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
				.title("Test Product")
				.price(new BigDecimal(10))
				.productId(UUID.randomUUID().toString())
				.quantity(1)
				.build();
		
		String messageId = UUID.randomUUID().toString();
		String messageKey = productCreatedEvent.getProductId();
		
		ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(
				"product-created-events-topic",
				messageKey,
				productCreatedEvent				
				);
		
		producerRecord.headers().add("messageId", messageId.getBytes());
		producerRecord.headers().add(KafkaHeaders.RECEIVED_KEY, messageId.getBytes());
		
		ProcessedEventEntity processedEventEntity = new ProcessedEventEntity();
		
		// When
		when(processedEventRepository.findByMessageId(anyString())).thenReturn(processedEventEntity);
		when(processedEventRepository.save(any(ProcessedEventEntity.class))).thenReturn(null);
		
		String responseBody = "{\"key\":\"value\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<String> responseEntity = 
				new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
		
		when(restTemplate.exchange(any(String.class), 
				any(HttpMethod.class), 
				isNull(), 
				eq(String.class)))
		.thenReturn(responseEntity);
		
		// Then
		kafkaTemplate.send(producerRecord).get();
		
		ArgumentCaptor<String> messageIdCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> messageKeyCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<ProductCreatedEvent> eventCaptor = ArgumentCaptor.forClass(ProductCreatedEvent.class);
	
		verify(productCreatedEventHandler, timeout(5000).times(1))
			.handle(eventCaptor.capture(), 
					messageIdCaptor.capture(), 
					messageKeyCaptor.capture());
		
		assertEquals(messageId, messageIdCaptor.getValue());
		assertEquals(messageKey, messageKeyCaptor.getValue());
		assertEquals(productCreatedEvent.getProductId(), eventCaptor.getValue().getProductId());
	}
}
