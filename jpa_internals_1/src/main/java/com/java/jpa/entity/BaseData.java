package com.java.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

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
public class BaseData implements Serializable {
	private static final long serialVersionUID = 8883715387022232251L;
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private long id;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "BINARY(16)")
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