package com.java.jpa.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseSubject implements Serializable{
	private static final long serialVersionUID = 7339198008998014969L;
	
	private String rollno;
    private String firstname;
    private String lastname;
    
    private String courseno;
    private String coursename;
    private String subject;
    private String courseType;
    
    private String subjectno;
    private String subjectname;
    private String textBook;
}