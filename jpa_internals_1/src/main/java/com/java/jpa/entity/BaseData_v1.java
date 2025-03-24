package com.java.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@MappedSuperclass
public class BaseData_v1 implements Serializable {
	private static final long serialVersionUID = 8883715387022232251L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
	private UUID id;
	
	@Column(name = "createdBy")
	private String createdBy = "admin";
	
	@Column(name = "createdAt")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name = "modifiedBy")
	private String modifiedBy = "admin";
	
	@Column(name = "modifiedOn")
	private LocalDateTime modifiedOn = LocalDateTime.now();
}