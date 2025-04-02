package com.java.email.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "processed-events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessedEventEntity implements Serializable {
	private static final long serialVersionUID = 8768659058059613996L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, unique = true)
	private String messageId;
	
	@Column(nullable = false)
	private String productId;
}
