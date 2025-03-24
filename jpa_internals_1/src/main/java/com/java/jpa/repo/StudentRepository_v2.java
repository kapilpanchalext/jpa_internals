package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Student_v2;

public interface StudentRepository_v2 extends JpaRepository<Student_v2, UUID>{

	Student_v2 findByRollno(String rollNo);

}