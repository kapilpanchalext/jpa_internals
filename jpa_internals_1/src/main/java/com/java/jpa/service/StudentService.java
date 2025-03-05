package com.java.jpa.service;

import java.util.List;

import com.java.jpa.entity.Course;
import com.java.jpa.entity.Student;
import com.java.jpa.entity.Subject;
import com.java.jpa.model.StudentCourseSubject;

public interface StudentService {

	Student saveStudent(Student student);

	Course saveCourse(Course course);

	Student findStudentById(String rollno);

	Course findCourseById(String courseno);

	Student findStudentByRollNo(String rollno);

	Course findCourseByCourseNo(String courseno);

	List<Student> getStudentsList();

	List<Course> getCourseList();

	Student assignCoursesToStudents(String rollNo, String courseNo);

	Subject saveSubject(Subject subject);

	Course assignSubjectToCourse(String courseNo, String subjectNo);

	List<StudentCourseSubject> getStudentCourseSubjectMappings();

}