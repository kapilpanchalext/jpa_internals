package com.java.products.controller;

import com.java.products.exception.ErrorMessage;
import com.java.products.model.CreateProductRestModel;
import com.java.products.service.ProductService;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
	private final ProductService productService;

	@PostMapping
	public ResponseEntity<Object> createProduct(@RequestBody CreateProductRestModel product) {
		String productId;

		try {
			productId = productService.createProduct(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorMessage(new Date(), e.getMessage(), "/products"));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(productId);
	}
}
