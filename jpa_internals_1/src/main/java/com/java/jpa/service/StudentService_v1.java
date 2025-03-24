package com.java.jpa.service;

import java.util.List;

import com.java.jpa.entity.Course_v1;
import com.java.jpa.entity.Student_v1;
import com.java.jpa.entity.Subject_v1;
import com.java.jpa.model.CourseModel;
import com.java.jpa.model.StudentCourse;
import com.java.jpa.model.StudentCourseSubject;
import com.java.jpa.model.StudentModel;
import com.java.jpa.model.SubjectModel;

@Deprecated
public interface StudentService_v1 {
	@Deprecated
	Student_v1 saveStudent(Student_v1 student);
	@Deprecated
	Course_v1 saveCourse(Course_v1 course);
	@Deprecated
	Student_v1 findStudentById(String rollno);
	@Deprecated
	Course_v1 findCourseById(String courseno);
	@Deprecated
	Student_v1 findStudentByRollNo(String rollno);
	@Deprecated
	Course_v1 findCourseByCourseNo(String courseno);
	@Deprecated
	List<StudentModel> getStudentsList();
	@Deprecated
	List<CourseModel> getCourseList();
	@Deprecated
	Student_v1 assignCoursesToStudents(String rollNo, String courseNo);
	@Deprecated
	Subject_v1 saveSubject(Subject_v1 subject);
	@Deprecated
	Course_v1 assignSubjectToCourse(String courseNo, String subjectNo);
	@Deprecated
	List<StudentCourseSubject> getStudentCourseSubjectMappings();
	@Deprecated
	List<StudentCourseSubject> getStudentsListBySubject(String subject);
	@Deprecated
	List<StudentCourseSubject> getStudentsListBySubjectNo(String subjectno);
	@Deprecated
	List<SubjectModel> getSubjectList();
	@Deprecated
	List<Student_v1> getStudentsListEntity();
	@Deprecated
	List<StudentCourse> getStudentByRollNo(int rollNo);

}