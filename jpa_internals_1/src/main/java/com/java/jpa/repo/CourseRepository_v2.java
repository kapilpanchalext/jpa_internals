package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Course_v2;

public interface CourseRepository_v2 extends JpaRepository<Course_v2, UUID>{

	Course_v2 findByCourseno(String courseNo);

}