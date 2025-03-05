package com.java.jpa.service;

import org.springframework.stereotype.Service;

import com.java.jpa.repo.CourseRepository;
import com.java.jpa.repo.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	
	private final CourseRepository courseRepository;
}
