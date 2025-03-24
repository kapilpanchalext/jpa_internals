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

@Deprecated
public interface StudentService_v1 {
	@Deprecated
	Student saveStudent(Student student);
	@Deprecated
	Course saveCourse(Course course);
	@Deprecated
	Student findStudentById(String rollno);
	@Deprecated
	Course findCourseById(String courseno);
	@Deprecated
	Student findStudentByRollNo(String rollno);
	@Deprecated
	Course findCourseByCourseNo(String courseno);
	@Deprecated
	List<StudentModel> getStudentsList();
	@Deprecated
	List<CourseModel> getCourseList();
	@Deprecated
	Student assignCoursesToStudents(String rollNo, String courseNo);
	@Deprecated
	Subject saveSubject(Subject subject);
	@Deprecated
	Course assignSubjectToCourse(String courseNo, String subjectNo);
	@Deprecated
	List<StudentCourseSubject> getStudentCourseSubjectMappings();
	@Deprecated
	List<StudentCourseSubject> getStudentsListBySubject(String subject);
	@Deprecated
	List<StudentCourseSubject> getStudentsListBySubjectNo(String subjectno);
	@Deprecated
	List<SubjectModel> getSubjectList();
	@Deprecated
	List<Student> getStudentsListEntity();
	@Deprecated
	List<StudentCourse> getStudentByRollNo(int rollNo);

}