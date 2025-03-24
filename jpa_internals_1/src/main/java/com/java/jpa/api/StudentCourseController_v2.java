package com.java.jpa.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.jpa.model.StudentModel;
import com.java.jpa.service.StudentService_v2;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v2")
@RequiredArgsConstructor
public class StudentCourseController_v2 {

	private final StudentService_v2 service;
	
	@GetMapping(path = "/getStudentsList")
	public ResponseEntity<List<StudentModel>> getStudentsList() {
		List<StudentModel> studentsList = service.getStudentsList();
		return ResponseEntity.status(HttpStatus.OK).body(studentsList);
	}
}