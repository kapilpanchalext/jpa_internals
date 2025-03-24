package com.java.jpa.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "student", catalog = "jpa_internals")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Student_v2 extends BaseData_v1 implements Serializable {
	private static final long serialVersionUID = 6857761803112680669L;
	
	@Column(name = "rollno", unique = true, updatable = false, length = 30)
	private String rollno;
	
	@Column(name = "firstname", length = 100)
	private String firstname;
	
	@Column(name = "lastname", length = 100)
	private String lastname;
	
	@Column(name = "dob")
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 30)
	private Gender_v1 gender;
	
	@Column(name = "address", length = 255)
	private String address;
	
	@Column(name = "mobileno")
	private long mobileNumber;
	
	@Column(name = "age")
	private byte age;
	
	@Column(name = "joiningdate")
	private LocalDate joiningDate;
	
	@Column(name = "isStudent")
	private boolean isStudent;
}