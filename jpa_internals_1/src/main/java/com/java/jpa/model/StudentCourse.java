package com.java.jpa.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StudentCourse implements Serializable {
	private static final long serialVersionUID = 6966584934030489819L;
	
	private String rollno;
	private String courseno;
	
	private String firstname;
	private String lastname;
	private String address;
	private long mobileNumber;
	private byte age;
	private boolean isStudent;
	private int courseRegisteredIn;

	private String coursename;
	private String subject;
	private String courseType;
	private boolean location;
}