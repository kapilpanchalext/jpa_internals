package com.java.products.model;

import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreationRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank
  private String name;

	@NotNull
  @Positive
  private BigDecimal price;

	@Positive
  private int quantity;
}
