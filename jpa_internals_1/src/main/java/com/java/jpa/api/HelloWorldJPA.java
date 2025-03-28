package com.java.jpa.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class HelloWorldJPA {
	private final KafkaTemplate<String, String> template;

	@GetMapping(path = "/helloworld")
	public ResponseEntity<String> getHelloWorld() {
		return ResponseEntity
					.status(HttpStatus.OK)
					.body("Helloworld from JPA Internals");
	}
	
	@PostMapping(path = "/postMessage")
	public ResponseEntity<String> sendKafkaMessage(@RequestBody String message) {
		template.send("helloworld-topic1", message);
		System.err.println(message);
		return ResponseEntity
					.status(HttpStatus.OK)
					.body("Message Sent Successfully!");
	}
	
	@PostMapping(path = "/message")
	public ResponseEntity<String> sendKafkaMessageMany() throws InterruptedException {
		for(int i = 0; i<Integer.MAX_VALUE; i++) {
			template.send("helloworld-topic1", "This is message no: " + i + " of total messages.");
			Thread.sleep(1000);
		}
		return ResponseEntity
					.status(HttpStatus.OK)
					.body("Message Sent Successfully!");
	}
}