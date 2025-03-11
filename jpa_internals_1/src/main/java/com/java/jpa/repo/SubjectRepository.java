package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, UUID>{

	Subject findBySubjectno(String subjectNo);

}
