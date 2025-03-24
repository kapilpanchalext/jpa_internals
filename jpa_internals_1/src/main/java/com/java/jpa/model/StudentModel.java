package com.java.jpa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.java.jpa.entity.Gender_v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StudentModel implements Serializable {
	private static final long serialVersionUID = 8626968666830043626L;
	
	private String rollno;
	private String firstname;
	private String lastname;
	private LocalDate dob;
	private Gender_v1 gender;
	private String address;
	private long mobileNumber;
	private byte age;
	private LocalDate joiningDate;
	private boolean isStudent;
	private List<CourseModel> courses;
}