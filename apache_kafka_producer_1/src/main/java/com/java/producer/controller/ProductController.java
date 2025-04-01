package com.java.producer.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.producer.exception.ErrorMessage;
import com.java.producer.model.CreateProductRestModel;
import com.java.producer.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final ProductService productService;

	@PostMapping(path = "/sendMessage")
	public ResponseEntity<String> createProducts(@RequestBody CreateProductRestModel product) {
		String productId;
		try {
			productId = productService.createProduct(product);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(productId);
		} catch (Exception e) {
//			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ErrorMessage
							.builder()
							.timestamp(LocalDate.now())
							.message(e.getMessage())
							.details("/products/sendMessage")
							.build()
							.toString());
		}
	}
}