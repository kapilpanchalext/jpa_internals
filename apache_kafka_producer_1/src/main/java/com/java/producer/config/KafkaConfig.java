package com.java.producer.config;

import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
	
//	@Value("${spring.kafka.bootstrap-servers}")
//	private String bootstrapServers;
//	
//	@Value("${spring.kafka.producer.acks}")
//	private String acks;
//	
//	@Value("${spring.kafka.producer.key-serializer}")
//	private String keySerializer;
//	
//	@Value("${spring.kafka.producer.value-serializer}")
//	private String valueSerializer;
//	
//	@Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
//	private String deliveryTimeouts;
//	
//	@Value("${spring.kafka.producer.properties.linger.ms}")
//	private String lingerMsConfig;
//	
//	@Value("${spring.kafka.producer.properties.request.timeout.ms}")
//	private String producerRequestTimeout;
//	
//	Map<String, Object> producerConfigs() {
//		Map<String, Object> config = new HashMap<>();
//		
//		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//		config.put(ProducerConfig.ACKS_CONFIG, acks);
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config);
//		config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeouts);
//		config.put(ProducerConfig.LINGER_MS_CONFIG, lingerMsConfig);
//		config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, producerRequestTimeout);
//		
//		return config;
//	}
//	
//	@Bean
//	ProducerFactory<String, ProductCreatedEvent> producerFactory() {
//		return new DefaultKafkaProducerFactory<>(producerConfigs());
//	}
//	
//	@Bean
//	KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate(){
//		return new KafkaTemplate<>(producerFactory());
//	}

	@Bean
	NewTopic createTopic() {
		return TopicBuilder
					.name("product-created-events-topic")
					.partitions(3)
					.replicas(3)
					.configs(Map.of("min.insync.replicas","2"))
					.build();
	}
}