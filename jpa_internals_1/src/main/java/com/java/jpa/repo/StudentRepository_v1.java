package com.java.jpa.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Student_v1;

@Deprecated
public interface StudentRepository_v1 extends JpaRepository<Student_v1, UUID> {
	@Deprecated
	Student_v1 findByRollno(String rollno);
	@Deprecated
	List<Student_v1> findAllByCoursesSubjectsSubjectname(String subject);
	@Deprecated
	List<Student_v1> findAllByCoursesSubjectsSubjectno(String subjectno);

}