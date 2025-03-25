package com.java.jpa.service;

import java.util.List;

import com.java.jpa.model.CourseModel;
import com.java.jpa.model.StudentModel;
import com.java.jpa.model.SubjectModel;
import com.java.jpa.model.TextBookModel;

public interface StudentService_v2 {

	List<StudentModel> getStudentsList();
	List<CourseModel> getCoursesList();
	List<SubjectModel> getSubjectsList();
	List<TextBookModel> getTextBooksList();
	void assignCourseToStudent(String courseNo, String rollNo);
	void assignTextBookToSubject(String isbn, String subjectNo);
	void saveNewCourse(CourseModel course);
	void assignSubjectToCourse(String courseNo, String subjectNo);
}