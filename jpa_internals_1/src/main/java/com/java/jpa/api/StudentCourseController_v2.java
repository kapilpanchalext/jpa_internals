package com.java.jpa.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.jpa.model.CourseModel;
import com.java.jpa.model.StudentModel;
import com.java.jpa.model.SubjectModel;
import com.java.jpa.model.TextBookModel;
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
	
	@GetMapping(path = "/getCoursesList")
	public ResponseEntity<List<CourseModel>> getCoursesList() {
		List<CourseModel> coursesList = service.getCoursesList();
		return ResponseEntity.status(HttpStatus.OK).body(coursesList);
	}
	
	@GetMapping(path = "/getSubjectsList")
	public ResponseEntity<List<SubjectModel>> getSubjectsList() {
		List<SubjectModel> subjectsList = service.getSubjectsList();
		return ResponseEntity.status(HttpStatus.OK).body(subjectsList);
	}
	
	@GetMapping(path = "/getTextBooksList")
	public ResponseEntity<List<TextBookModel>> getTextBooksList() {
		List<TextBookModel> subjectsList = service.getTextBooksList();
		return ResponseEntity.status(HttpStatus.OK).body(subjectsList);
	}
	
	@PutMapping(path = "/assignCourseToStudent")
	public ResponseEntity<String> assignCourseToStudent(@RequestParam String courseNo, @RequestParam String rollNo){
		service.assignCourseToStudent(courseNo, rollNo);
		return ResponseEntity.status(HttpStatus.OK).body("Student Assigned to Course Successfully!");
	}
	
	@PutMapping(path = "/assignSubjectToCourse")
	public ResponseEntity<String> assignSubjectToCourse(@RequestParam String subjectNo, @RequestParam String courseNo){
		service.assignSubjectToCourse(courseNo, subjectNo);
		return ResponseEntity.status(HttpStatus.OK).body("Subject Assigned to Course Successfully!");
	}
	
	@PutMapping(path = "/assignTextBookToSubject")
	public ResponseEntity<String> assignTextBookToSubject(@RequestParam String isbn, @RequestParam String subjectNo){
		service.assignTextBookToSubject(isbn, subjectNo);
		return ResponseEntity.status(HttpStatus.OK).body("TextBook Assigned to Subject Successfully!");
	}
	
	@PostMapping(path = "/saveNewCourse")
	public ResponseEntity<String> saveNewCourse(@RequestBody CourseModel course){
		service.saveNewCourse(course);
		return ResponseEntity
					.status(HttpStatus.OK)
					.body("Course Saved Successfully!");
	}
}