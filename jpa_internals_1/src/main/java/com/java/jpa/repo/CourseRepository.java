package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Course;

public interface CourseRepository extends JpaRepository<Course, UUID>{

	Course findByCourseno(String courseno);

	Course findByCourseno(Long valueOf);

}