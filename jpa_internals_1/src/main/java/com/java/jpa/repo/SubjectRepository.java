package com.java.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	Subject findBySubjectno(String subjectNo);

}
