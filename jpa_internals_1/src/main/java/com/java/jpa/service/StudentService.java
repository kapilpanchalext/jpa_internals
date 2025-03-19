package com.java.jpa.service;

import java.util.List;

import com.java.jpa.entity.Course;
import com.java.jpa.entity.Student;
import com.java.jpa.entity.Subject;
import com.java.jpa.model.CourseModel;
import com.java.jpa.model.StudentCourse;
import com.java.jpa.model.StudentCourseSubject;
import com.java.jpa.model.StudentModel;
import com.java.jpa.model.SubjectModel;

public interface StudentService {

	Student saveStudent(Student student);

	Course saveCourse(Course course);

	Student findStudentById(String rollno);

	Course findCourseById(String courseno);

	Student findStudentByRollNo(String rollno);

	Course findCourseByCourseNo(String courseno);

	List<StudentModel> getStudentsList();

	List<CourseModel> getCourseList();

	Student assignCoursesToStudents(String rollNo, String courseNo);

	Subject saveSubject(Subject subject);

	Course assignSubjectToCourse(String courseNo, String subjectNo);

	List<StudentCourseSubject> getStudentCourseSubjectMappings();

	List<StudentCourseSubject> getStudentsListBySubject(String subject);

	List<StudentCourseSubject> getStudentsListBySubjectNo(String subjectno);

	List<SubjectModel> getSubjectList();

	List<Student> getStudentsListEntity();

	List<StudentCourse> getStudentByRollNo(int rollNo);

}