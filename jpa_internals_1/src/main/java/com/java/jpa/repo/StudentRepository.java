package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

	Student findByRollno(String rollno);

}