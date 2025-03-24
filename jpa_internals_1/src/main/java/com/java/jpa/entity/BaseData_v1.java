package com.java.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

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
	@UuidGenerator
	@Column(name = "id", updatable = false, nullable = false, length = 36)
	private UUID id;
	
	@Column(name = "createdby", length = 50)
	private String createdBy = "admin";
	
	@Column(name = "createdat")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name = "modifiedby", length = 50)
	private String modifiedBy = "admin";
	
	@Column(name = "modifiedon")
	private LocalDateTime modifiedOn = LocalDateTime.now();
}