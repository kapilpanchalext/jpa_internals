package com.java.jpa.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.jpa.entity.Student_v2;
import com.java.jpa.model.StudentModel;
import com.java.jpa.repo.CourseRepository_v2;
import com.java.jpa.repo.StudentRepository_v2;
import com.java.jpa.repo.SubjectRepository_v2;
import com.java.jpa.repo.TextBookRepository_v1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl_v2 implements StudentService_v2 {

	private final StudentRepository_v2 studentRepository;
	private final CourseRepository_v2 courseRepository;
	private final SubjectRepository_v2 subjectRepository;
	private final TextBookRepository_v1 textBookRepository;

	@Override
	public List<StudentModel> getStudentsList() {
		List<Student_v2> getAllStudentsList = studentRepository.findAll();
		List<StudentModel> studentModelList = new ArrayList<>();
		
		for(Student_v2 element : getAllStudentsList) {
			studentModelList.add(StudentModel.builder()
					.firstname(element.getFirstname())
					.lastname(element.getLastname())
					.dob(element.getDob())
					.gender(element.getGender())
					.age((byte)Period.between(element.getDob(), LocalDate.now()).getYears())
					.address(element.getAddress())
					.rollno(element.getRollno())
					.isCurrent(element.isCurrent())
					.joiningDate(element.getJoiningDate())
					.mobileNumber(element.getMobileNumber())
					.build());
		}
		
		return studentModelList;
	}
}