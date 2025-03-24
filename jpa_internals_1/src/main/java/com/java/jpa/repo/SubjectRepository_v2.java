package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Subject_v2;

public interface SubjectRepository_v2 extends JpaRepository<Subject_v2, UUID>{

}