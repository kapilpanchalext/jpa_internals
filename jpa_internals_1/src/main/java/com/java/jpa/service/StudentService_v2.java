package com.java.jpa.service;

import java.util.List;

import com.java.jpa.model.CourseModel;
import com.java.jpa.model.StudentModel;
import com.java.jpa.model.SubjectModel;

public interface StudentService_v2 {

	List<StudentModel> getStudentsList();
	List<CourseModel> getCoursesList();
	List<SubjectModel> getSubjectsList();
}