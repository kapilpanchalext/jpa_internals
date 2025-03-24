package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Course;

@Deprecated
public interface CourseRepository extends JpaRepository<Course, UUID>{
	@Deprecated
	Course findByCourseno(String courseno);

}