package com.java.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.java.jpa.entity.Course_v1;
import com.java.jpa.entity.Student_v1;
import com.java.jpa.model.StudentCourse;

@Deprecated
@Mapper(componentModel = "spring")
public interface MapperStudentCourseToStudentCourseModel {
	MapperStudentCourseToStudentCourseModel INSTANCE = Mappers.getMapper(MapperStudentCourseToStudentCourseModel.class);

//    @Mapping(source = "student.rollno", target = "rollno")
//    @Mapping(source = "student.firstname", target = "firstname")
//    @Mapping(source = "student.lastname", target = "lastname")
//    @Mapping(source = "student.address", target = "address")
//    @Mapping(source = "student.mobileNumber", target = "mobileNumber")
//    @Mapping(source = "student.age", target = "age")
//    @Mapping(source = "student.student", target = "student")
//    @Mapping(source = "course.courseno", target = "courseno")
//    @Mapping(source = "course.coursename", target = "coursename")
//    @Mapping(source = "course.courseType", target = "courseType")
//    @Mapping(source = "course.location", target = "location")
//    StudentCourse toStudentCourse(Student_v1 student, Course_v1 course);
//    
//    @Mapping(source = "rollno", target = "rollno")
//    @Mapping(source = "firstname", target = "firstname")
//    @Mapping(source = "lastname", target = "lastname")
//    @Mapping(source = "address", target = "address")
//    @Mapping(source = "mobileNumber", target = "mobileNumber")
//    @Mapping(source = "age", target = "age")
//    @Mapping(source = "student", target = "isStudent")
//    Student_v1 toStudent(StudentCourse studentCourse);
//
//    @Mapping(source = "courseno", target = "courseno")
//    @Mapping(source = "coursename", target = "coursename")
//    @Mapping(source = "courseType", target = "courseType")
//    @Mapping(source = "location", target = "location")
//    Course_v1 toCourse(StudentCourse studentCourse);
}