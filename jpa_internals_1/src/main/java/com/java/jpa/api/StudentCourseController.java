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

import com.java.jpa.entity.Course;
import com.java.jpa.entity.Student;
import com.java.jpa.entity.Subject;
import com.java.jpa.mapper.MapperStudentCourseToStudentCourseModel;
import com.java.jpa.model.StudentCourse;
import com.java.jpa.model.StudentCourseSubject;
import com.java.jpa.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class StudentCourseController {

	private final StudentService service;
	
	@GetMapping(path = "/getStudentDetails")
	public ResponseEntity<StudentCourse> getStudentDetails(@RequestParam String rollNo, @RequestParam String courseNo) {
		
//		Student student = new Student("R123", "John", "Doe", "123 Street", 1234567890L, (byte) 20, true, 101);
//		Course course = new Course("C001", "Mathematics", "Algebra", "Online", true);

		Student student = service.findStudentByRollNo(rollNo);
		Course course = service.findCourseByCourseNo(courseNo);
		StudentCourse studentCourse = MapperStudentCourseToStudentCourseModel.INSTANCE.toStudentCourse(student, course);

		System.out.println(studentCourse);
		
		return ResponseEntity.status(HttpStatus.OK).body(studentCourse);
	}
	
	@PostMapping(path = "/saveStudentCourseDetails")
	public ResponseEntity<String> getStudentDetails(@RequestBody StudentCourse studentCourse) {
		
//		Student student = MapperStudentCourseToStudentCourseModel.INSTANCE.toStudent(studentCourse);
//		Course course = MapperStudentCourseToStudentCourseModel.INSTANCE.toCourse(studentCourse);
//		
//		service.saveStudent(student);
//		service.saveCourse(course);
//		
//		return ResponseEntity.status(HttpStatus.OK).body("Successful!");
		
		// Convert DTO to Entity
	    Student student = MapperStudentCourseToStudentCourseModel.INSTANCE.toStudent(studentCourse);
	    Course course = MapperStudentCourseToStudentCourseModel.INSTANCE.toCourse(studentCourse);

	    // Check if student already exists
	    Student existingStudent = student;
	    
	    // Check if course already exists
	    Course existingCourse = course;
	    
	    // Establish Many-to-Many relationship
	    existingStudent.getCourses().add(course);
//	    existingCourse.getStudents().add(student);
//	    student.getCourses().add(course);
//	    course.getStudents().add(student);
	    // Save student and course
	    Student savedStudent = service.saveStudent(existingStudent);
	    service.saveCourse(existingCourse);

	    return ResponseEntity
	    			.status(HttpStatus.OK)
	    			.body("Student successfully registered for the course!");
	}
	
	@PostMapping(path = "/saveStudent")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		Student savedStudent = service.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(savedStudent);
	}
	
	@PostMapping(path = "/saveCourse")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
		Course savedCourse = service.saveCourse(course);
		return ResponseEntity.status(HttpStatus.OK).body(savedCourse);
	}
	
	@GetMapping(path = "/getStudentsList")
	public ResponseEntity<List<Student>> getStudentsList() {
		List<Student> studentsList = service.getStudentsList();
		return ResponseEntity.status(HttpStatus.OK).body(studentsList);
	}
	
	@GetMapping(path = "/getCourseList")
	public ResponseEntity<List<Course>> getCourseList() {
		List<Course> courseList = service.getCourseList();
		return ResponseEntity.status(HttpStatus.OK).body(courseList);
	}
	
	@PutMapping(path = "/assignCoursesToStudents")
	public ResponseEntity<Student> assignCoursesToStudents(@RequestParam String rollNo, @RequestParam String courseNo){
		Student student = service.assignCoursesToStudents(rollNo, courseNo);
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
	
	@PostMapping(path = "/saveSubject")
	public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject){
		Subject savedSubject = service.saveSubject(subject);
		return ResponseEntity.status(HttpStatus.OK).body(savedSubject);
	}
	
	@PutMapping(path = "/assignSubjectToCourse")
	public ResponseEntity<Course> assignSubjectToCourse(@RequestParam String subjectNo, @RequestParam String courseNo){
		Course savedCourse = service.assignSubjectToCourse(courseNo, subjectNo);
		return ResponseEntity.status(HttpStatus.OK).body(savedCourse);
	}
	
	@GetMapping("/courses-subjects")
    public List<StudentCourseSubject> getStudentCourseSubjectMappings() {
        return service.getStudentCourseSubjectMappings();
    }
}