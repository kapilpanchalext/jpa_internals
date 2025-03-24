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
		Student student = service.findStudentByRollNo(rollNo);
		Course course = service.findCourseByCourseNo(courseNo);
		StudentCourse studentCourse = MapperStudentCourseToStudentCourseModel.INSTANCE.toStudentCourse(student, course);
		System.out.println(studentCourse);
		return ResponseEntity.status(HttpStatus.OK).body(studentCourse);
	}
	
	@Deprecated
	@PostMapping(path = "/saveStudentCourseDetails")
	public ResponseEntity<String> saveStudentCourseDetails(@RequestBody StudentCourse studentCourse) {
		
		// Convert DTO to Entity
	    Student student = MapperStudentCourseToStudentCourseModel.INSTANCE.toStudent(studentCourse);
	    Course course = MapperStudentCourseToStudentCourseModel.INSTANCE.toCourse(studentCourse);

	    // Check if student already exists
	    Student existingStudent = student;
	    
	    // Check if course already exists
	    Course existingCourse = course;
	    
	    // Establish Many-to-Many relationship
	    existingStudent.getCourses().add(course);
	    // Save student and course
	    Student savedStudent = service.saveStudent(existingStudent);
	    service.saveCourse(existingCourse);

	    return ResponseEntity
	    			.status(HttpStatus.OK)
	    			.body("Student successfully registered for the course!");
	}
	
	@Deprecated
	@PostMapping(path = "/saveStudent")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		Student savedStudent = service.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(savedStudent);
	}
	
	@Deprecated
	@PostMapping(path = "/saveCourse")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
		Course savedCourse = service.saveCourse(course);
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
	public ResponseEntity<List<Student>> getStudentsListEntity() {
		List<Student> studentsList = service.getStudentsListEntity();
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
	public ResponseEntity<Student> assignCoursesToStudents(@RequestParam String rollNo, @RequestParam String courseNo){
		Student student = service.assignCoursesToStudents(rollNo, courseNo);
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
	
	@Deprecated
	@PostMapping(path = "/saveSubject")
	public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject){
		Subject savedSubject = service.saveSubject(subject);
		return ResponseEntity.status(HttpStatus.OK).body(savedSubject);
	}
	
	@Deprecated
	@PutMapping(path = "/assignSubjectToCourse")
	public ResponseEntity<Course> assignSubjectToCourse(@RequestParam String subjectNo, @RequestParam String courseNo){
		Course savedCourse = service.assignSubjectToCourse(courseNo, subjectNo);
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