package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Subject_v1;
@Deprecated
public interface SubjectRepository_v1 extends JpaRepository<Subject_v1, UUID>{
	@Deprecated
	Subject_v1 findBySubjectno(String subjectNo);

}
