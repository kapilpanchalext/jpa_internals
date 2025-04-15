package com.java.products.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreationResponse implements Serializable{
	private static final long serialVersionUID = 1L;

	private UUID id;
  private String name;
  private BigDecimal price;
  private int quantity;
}
