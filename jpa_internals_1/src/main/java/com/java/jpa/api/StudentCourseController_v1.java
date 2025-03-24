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

import com.java.jpa.entity.Course_v1;
import com.java.jpa.entity.Student_v1;
import com.java.jpa.entity.Subject_v1;
import com.java.jpa.model.CourseModel;
import com.java.jpa.model.StudentCourse;
import com.java.jpa.model.StudentCourseSubject;
import com.java.jpa.model.StudentModel;
import com.java.jpa.model.SubjectModel;
import com.java.jpa.service.StudentService_v1;

import lombok.RequiredArgsConstructor;

@Deprecated
@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class StudentCourseController_v1 {

	private final StudentService_v1 service;
	
	@Deprecated
	@GetMapping(path = "/getStudentDetails")
	public ResponseEntity<StudentCourse> getStudentDetails(@RequestParam String rollNo, @RequestParam String courseNo) {
//		Student_v1 student = service.findStudentByRollNo(rollNo);
//		Course_v1 course = service.findCourseByCourseNo(courseNo);
//		StudentCourse studentCourse = MapperStudentCourseToStudentCourseModel.INSTANCE.toStudentCourse(student, course);
//		System.out.println(studentCourse);
//		return ResponseEntity.status(HttpStatus.OK).body(studentCourse);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@Deprecated
	@PostMapping(path = "/saveStudentCourseDetails")
	public ResponseEntity<String> saveStudentCourseDetails(@RequestBody StudentCourse studentCourse) {
		
		// Convert DTO to Entity
//	    Student_v1 student = MapperStudentCourseToStudentCourseModel.INSTANCE.toStudent(studentCourse);
//	    Course_v1 course = MapperStudentCourseToStudentCourseModel.INSTANCE.toCourse(studentCourse);
//
//	     Check if student already exists
//	    Student_v1 existingStudent = student;
//	    
//	    // Check if course already exists
//	    Course_v1 existingCourse = course;
//	    
//	    // Establish Many-to-Many relationship
//	    existingStudent.getCourses().add(course);
//	    // Save student and course
//	    Student_v1 savedStudent = service.saveStudent(existingStudent);
//	    service.saveCourse(existingCourse);

	    return ResponseEntity
	    			.status(HttpStatus.OK)
	    			.body("Student successfully registered for the course!");
	}
	
	@Deprecated
	@PostMapping(path = "/saveStudent")
	public ResponseEntity<Student_v1> saveStudent(@RequestBody Student_v1 student) {
		Student_v1 savedStudent = service.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(savedStudent);
	}
	
	@Deprecated
	@PostMapping(path = "/saveCourse")
	public ResponseEntity<Course_v1> saveCourse(@RequestBody Course_v1 course) {
		Course_v1 savedCourse = service.saveCourse(course);
		return ResponseEntity.status(HttpStatus.OK).body(savedCourse);
	}
	
	@Deprecated
	@GetMapping(path = "/getStudentsList")
	public ResponseEntity<List<StudentModel>> getStudentsList() {
		List<StudentModel> studentsList = service.getStudentsList();
		return ResponseEntity.status(HttpStatus.OK).body(studentsList);
	}
	
	@Deprecated
	@GetMapping(path = "/getStudentsListEntity")
	public ResponseEntity<List<Student_v1>> getStudentsListEntity() {
		List<Student_v1> studentsList = service.getStudentsListEntity();
		return ResponseEntity.status(HttpStatus.OK).body(studentsList);
	}
	
	@Deprecated
	@GetMapping(path = "/getCourseList")
	public ResponseEntity<List<CourseModel>> getCourseList() {
		List<CourseModel> courseList = service.getCourseList();
		return ResponseEntity.status(HttpStatus.OK).body(courseList);
	}
	
	@Deprecated
	@GetMapping(path = "/getSubjectList")
	public ResponseEntity<List<SubjectModel>> getSubjectList() {
		List<SubjectModel> subjectList = service.getSubjectList();
		return ResponseEntity.status(HttpStatus.OK).body(subjectList);
	}
	
	@Deprecated
	@PutMapping(path = "/assignCoursesToStudents")
	public ResponseEntity<Student_v1> assignCoursesToStudents(@RequestParam String rollNo, @RequestParam String courseNo){
		Student_v1 student = service.assignCoursesToStudents(rollNo, courseNo);
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
	
	@Deprecated
	@PostMapping(path = "/saveSubject")
	public ResponseEntity<Subject_v1> saveSubject(@RequestBody Subject_v1 subject){
		Subject_v1 savedSubject = service.saveSubject(subject);
		return ResponseEntity.status(HttpStatus.OK).body(savedSubject);
	}
	
	@Deprecated
	@PutMapping(path = "/assignSubjectToCourse")
	public ResponseEntity<Course_v1> assignSubjectToCourse(@RequestParam String subjectNo, @RequestParam String courseNo){
		Course_v1 savedCourse = service.assignSubjectToCourse(courseNo, subjectNo);
		return ResponseEntity.status(HttpStatus.OK).body(savedCourse);
	}
	
	@Deprecated
	@GetMapping(path = "/courses-subjects")
    public List<StudentCourseSubject> getStudentCourseSubjectMappings() {
        return service.getStudentCourseSubjectMappings();
    }
	
	@Deprecated
	@GetMapping(path = "/list-students-by-subject")
	public ResponseEntity<List<StudentCourseSubject>> getStudentsListBySubject(@RequestParam String subject) {
		List<StudentCourseSubject> studentsListBySubject = service.getStudentsListBySubject(subject);
		return ResponseEntity.status(HttpStatus.OK).body(studentsListBySubject);
	}
	
	@Deprecated
	@GetMapping(path = "/list-students-by-subjectno")
	public ResponseEntity<List<StudentCourseSubject>> getStudentsListBySubjectNo(@RequestParam String subjectno) {
		List<StudentCourseSubject> studentsListBySubject = service.getStudentsListBySubjectNo(subjectno);
		return ResponseEntity.status(HttpStatus.OK).body(studentsListBySubject);
	}
	
	@Deprecated
	@GetMapping(path = "/getStudentByRollno")
	public ResponseEntity<List<StudentCourse>> getStudentByRollNo(@RequestParam int rollNo) {
		List<StudentCourse> studentsList = service.getStudentByRollNo(rollNo);
		return ResponseEntity.status(HttpStatus.OK).body(studentsList);
	}
}