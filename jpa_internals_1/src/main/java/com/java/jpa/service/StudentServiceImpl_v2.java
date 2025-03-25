package com.java.jpa.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
			Set<Student_v2> studentList = element.getStudents();
			List<StudentModel> studentModelList = new ArrayList<>();
			for(Student_v2 innerElement : studentList) {
				studentModelList.add(StudentModel.builder()
						.rollno(innerElement.getRollno())
						.firstname(innerElement.getFirstname())
						.lastname(innerElement.getLastname())
						.address(innerElement.getAddress())
						.dob(innerElement.getDob())
						.joiningDate(innerElement.getJoiningDate())
						.gender(innerElement.getGender())
						.isCurrent(innerElement.isCurrent())
						.mobileNumber(innerElement.getMobileNumber())
						.build()
						);
			}
			
			Set<Subject_v2> subjectsList = element.getSubjects();
			List<SubjectModel> subjectModelList = new ArrayList<>();
			for(Subject_v2 innerElement : subjectsList) {
				
				Set<TextBook_v1> textbooksList = innerElement.getTextbooks();
				List<TextBookModel> textbookModelList = new ArrayList<>();
				
				for(TextBook_v1 innerInnerElement : textbooksList) {
					textbookModelList.add(TextBookModel.builder()
							.isbn(innerInnerElement.getIsbn())
							.textbookName(innerInnerElement.getTextbookName())
							.authorName(innerInnerElement.getAuthorName())
							.build());
				}
				
				subjectModelList.add(SubjectModel.builder()
						.subjectno(innerElement.getSubjectno())
						.subjectname(innerElement.getSubjectname())
						.textBookList(textbookModelList)
						.build());
			}
			
			courseModelList.add(CourseModel.builder()
					.courseno(element.getCourseno())
					.coursename(element.getCoursename())
					.courseType(element.getCourseType())
					.location(element.getLocation())
					.studentsList(studentModelList)
					.subjectsList(subjectModelList)
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
	public void assignCourseToStudent(String courseNo, String rollNo) {
		Student_v2 student = studentRepository.findByRollno(rollNo);
		Course_v2 course = courseRepository.findByCourseno(courseNo);
		course.getStudents().add(student);
		courseRepository.save(course);
	}

	@Transactional
	@Override
	public void assignTextBookToSubject(String isbn, String subjectNo) {
		Subject_v2 subject = subjectRepository.findBySubjectno(subjectNo);
		TextBook_v1 textBook = textBookRepository.findByIsbn(isbn);
		subject.getTextbooks().add(textBook);
		subjectRepository.save(subject);
	}

	@Transactional
	@Override
	public void saveNewCourse(CourseModel course) {
		System.err.println(course);
		Course_v2 saveCourse = Course_v2.builder()
				.coursename(course.getCoursename())
				.courseno(course.getCourseno())
				.courseType(course.getCourseType())
				.location(course.getLocation())
				.build();
		System.err.println(saveCourse);
		courseRepository.save(saveCourse);
	}
	
	@Transactional
	@Override
	public void assignSubjectToCourse(String courseNo, String subjectNo) {
		Course_v2 course = courseRepository.findByCourseno(courseNo);
		Subject_v2 subject = subjectRepository.findBySubjectno(subjectNo);
		course.getSubjects().add(subject);
		courseRepository.save(course);
	}
}