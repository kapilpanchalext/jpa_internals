package com.java.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}