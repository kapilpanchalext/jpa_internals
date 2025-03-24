package com.java.jpa.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.jpa.entity.Course_v2;
import com.java.jpa.entity.Student_v2;
import com.java.jpa.entity.Subject_v2;
import com.java.jpa.entity.TextBook_v1;
import com.java.jpa.model.CourseModel;
import com.java.jpa.model.StudentModel;
import com.java.jpa.model.SubjectModel;
import com.java.jpa.model.TextBookModel;
import com.java.jpa.repo.CourseRepository_v2;
import com.java.jpa.repo.StudentRepository_v2;
import com.java.jpa.repo.SubjectRepository_v2;
import com.java.jpa.repo.TextBookRepository_v1;

import jakarta.transaction.Transactional;
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

	@Override
	public List<CourseModel> getCoursesList() {
		List<Course_v2> getAllCoursesList = courseRepository.findAll();
		List<CourseModel> courseModelList = new ArrayList<>();
		
		for(Course_v2 element : getAllCoursesList) {
			courseModelList.add(CourseModel.builder()
					.courseno(element.getCourseno())
					.coursename(element.getCoursename())
					.courseType(element.getCourseType())
					.location(element.getLocation())
					.build());
		}
		return courseModelList;
	}

	@Override
	public List<SubjectModel> getSubjectsList() {
		List<Subject_v2> getAllSubjectsList = subjectRepository.findAll();
		List<SubjectModel> subjectsList = new ArrayList<>();
		
		for(Subject_v2 element : getAllSubjectsList) {
			subjectsList.add(SubjectModel.builder()
					.subjectname(element.getSubjectname())
					.subjectno(element.getSubjectno())
					.build());
		}
		return subjectsList;
	}

	@Override
	public List<TextBookModel> getTextBooksList() {
		List<TextBook_v1> textBooksList = textBookRepository.findAll();
		List<TextBookModel> textBookModelList = new ArrayList<>();
		
		for(TextBook_v1 element : textBooksList) {
			textBookModelList.add(TextBookModel.builder()
					.isbn(element.getIsbn())
					.textbookName(element.getTextbookName())
					.authorName(element.getAuthorName())
					.build());
		}
		return textBookModelList;
	}

	@Transactional
	@Override
	public CourseModel assignCourseToStudent(String courseNo, String rollNo) {
		Student_v2 student = studentRepository.findByRollno(rollNo);
		Course_v2 course = courseRepository.findByCourseno(courseNo);
		course.getStudents().add(student);
		
		Course_v2 savedCourse = courseRepository.save(course);
		
		StudentModel studentModelSaved = StudentModel.builder()
				.firstname(student.getFirstname())
				.lastname(student.getLastname())
				.address(student.getAddress())
				.age((byte)Period.between(student.getDob(), LocalDate.now()).getYears())
				.dob(student.getDob())
				.gender(student.getGender())
				.isCurrent(student.isCurrent())
				.joiningDate(student.getJoiningDate())
				.mobileNumber(student.getMobileNumber())
				.rollno(student.getRollno())
				.build();
		
		List<StudentModel> studentModelList = new ArrayList<>();
		studentModelList.add(studentModelSaved);
		
		CourseModel courseModelSaved = CourseModel.builder()
				.coursename(savedCourse.getCoursename())
				.courseno(savedCourse.getCourseno())
				.courseType(savedCourse.getCourseType())
				.location(savedCourse.getLocation())
				.studentsList(studentModelList)
				.build();
		
		return courseModelSaved;
	}
}