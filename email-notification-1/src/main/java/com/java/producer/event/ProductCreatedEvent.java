package com.java.producer.event;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreatedEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private String productId;
	private String title;
	private BigDecimal price;
	private int quantity;
}