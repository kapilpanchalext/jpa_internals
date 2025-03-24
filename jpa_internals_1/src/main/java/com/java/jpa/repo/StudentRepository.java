package com.java.jpa.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.Student;

@Deprecated
public interface StudentRepository extends JpaRepository<Student, UUID> {
	@Deprecated
	Student findByRollno(String rollno);
	@Deprecated
	List<Student> findAllByCoursesSubjectsSubjectname(String subject);
	@Deprecated
	List<Student> findAllByCoursesSubjectsSubjectno(String subjectno);

}