package com.java.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.java.products.event.ProductCreatedEvent;
import com.java.products.model.CreateProductRestModel;
import com.java.products.service.ProductService;

import lombok.RequiredArgsConstructor;

@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(value = "test")
@EmbeddedKafka(partitions = 3, count = 3, controlledShutdown = true)
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
public class ProductsServiceIntegrationTest {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker;
	
	@Autowired
	private Environment environment;
	
	private KafkaMessageListenerContainer<String, ProductCreatedEvent> container;
	private BlockingQueue<ConsumerRecord<String, ProductCreatedEvent>> records;
	
	@BeforeAll
	void setUp() {
		DefaultKafkaConsumerFactory<String, Object> consumerFactory = new DefaultKafkaConsumerFactory<>(getConsumerProperties());
		ContainerProperties containerProperties = new ContainerProperties(environment.getProperty("product-created-events-topic-name"));
		container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
		records = new LinkedBlockingQueue<>();
		container.setupMessageListener((MessageListener<String, ProductCreatedEvent>) records::add);
		container.start();
		ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
	}

	@Test
	void testCreateProduct_whenGivenValidProductDetails_successfulSendsKafkaMessage() throws Exception {
		
		// Given
		String title = "Title1";
		BigDecimal price = new BigDecimal(600);
		int quantity = 1;		
			
		CreateProductRestModel createProductRestModel = CreateProductRestModel.builder()
				.price(price)
				.title(title)
				.quantity(quantity)
				.build();

		// When
		productService.createProduct(createProductRestModel);
		
		// Then
		ConsumerRecord<String, ProductCreatedEvent> message = records.poll(3000, TimeUnit.MILLISECONDS);
		assertNotNull(message);
		assertNotNull(message.key());
		ProductCreatedEvent productCreatedEvent = message.value();
		assertEquals(createProductRestModel.getQuantity(), productCreatedEvent.getQuantity());
		assertEquals(createProductRestModel.getTitle(), productCreatedEvent.getTitle());
		assertEquals(createProductRestModel.getPrice(), productCreatedEvent.getPrice());
	}
	
	private Map<String, Object> getConsumerProperties(){
		return Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker.getBrokersAsString(),
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class,
				ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class,
				ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty("spring.kafka.consumer.group-id"),
				JsonDeserializer.TRUSTED_PACKAGES, environment.getProperty("spring.kafka.consumer.properties.spring.json.trusted.packages"),
				ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, environment.getProperty("spring.kafka.consumer.auto-offset-reset")
				);
	}
	
	@AfterAll
	void tearDown() {
		container.stop();
	}
}