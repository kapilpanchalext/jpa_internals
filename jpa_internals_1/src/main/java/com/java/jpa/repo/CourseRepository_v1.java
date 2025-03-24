package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Course;

@Deprecated
public interface CourseRepository_v1 extends JpaRepository<Course, UUID>{
	@Deprecated
	Course findByCourseno(String courseno);

}