package com.java.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}