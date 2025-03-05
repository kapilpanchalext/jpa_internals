package com.java.jpa.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.jpa.model.StudentCourse;
import com.java.jpa.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class StudentCourseController {

	private final StudentService service;
	
	@GetMapping(path = "/getStudentDetails")
	public ResponseEntity<StudentCourse> getStudentDetails(){
		return ResponseEntity.status(HttpStatus.OK).body(new StudentCourse());
	}
}