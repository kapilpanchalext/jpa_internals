package com.java.products.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	 @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;
   
	 @Column(name = "quantity")
   private int quantity;
   
	 @Column(name = "name")
   private String name;
   
	 @Column(name = "price")
   private BigDecimal price;
}
