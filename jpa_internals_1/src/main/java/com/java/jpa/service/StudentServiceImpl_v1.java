package com.java.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.jpa.entity.Course_v1;
import com.java.jpa.entity.Student_v1;
import com.java.jpa.entity.Subject_v1;
import com.java.jpa.model.CourseModel;
import com.java.jpa.model.StudentCourse;
import com.java.jpa.model.StudentCourseSubject;
import com.java.jpa.model.StudentModel;
import com.java.jpa.model.SubjectModel;
import com.java.jpa.repo.CourseRepository_v1;
import com.java.jpa.repo.StudentRepository_v1;
import com.java.jpa.repo.SubjectRepository_v1;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Deprecated
@Service
@RequiredArgsConstructor
public class StudentServiceImpl_v1 implements StudentService_v1 {

	private final StudentRepository_v1 studentRepository;	
	private final CourseRepository_v1 courseRepository;
	private final SubjectRepository_v1 subjectRepository;
	
	@Deprecated
	@Override
	public Student_v1 saveStudent(Student_v1 student) {
		return studentRepository.save(student);
	}
	
	@Deprecated
	@Override
	public Course_v1 saveCourse(Course_v1 course) {
		return courseRepository.save(course);		
	}
	
	@Deprecated
	@Override
	public Student_v1 findStudentById(String rollno) {
//		return studentRepository.findByRollno(rollno);
		return null;
	}
	
	@Deprecated
	@Override
	public Course_v1 findCourseById(String courseno) {
		return courseRepository.findByCourseno(courseno);
	}
	
	@Deprecated
	@Override
	public Student_v1 findStudentByRollNo(String rollno) {
		return studentRepository.findByRollno(rollno);
	}
	
	@Deprecated
	@Override
	public Course_v1 findCourseByCourseNo(String courseno) {
//		return courseRepository.findByCourseno(courseno);
		return null;
	}
	
	@Deprecated
//	@Cacheable(value = "students")
	@Override
	public List<StudentModel> getStudentsList() {
		List<Student_v1> studentsList = studentRepository.findAll();
		List<StudentModel> studentModelList = new ArrayList<>();
		
		for(Student_v1 element : studentsList) {
			List<CourseModel>courseModelList = new ArrayList<>();
			Set<Course_v1> courses = element.getCourses();
			
			for(Course_v1 innerElement : courses) {
				List<SubjectModel> subjectModelList = new ArrayList<>();
				Set<Subject_v1> subjects = innerElement.getSubjects();
				
				for(Subject_v1 innerInnerElement : subjects) {
					subjectModelList.add(SubjectModel.builder()
							.subjectname(innerInnerElement.getSubjectname())
							.subjectno(innerInnerElement.getSubjectno())
							.textBook(innerInnerElement.getTextBook())
							.build());
				}
				
				courseModelList.add(CourseModel.builder()
						.coursename(innerElement.getCoursename())
						.courseno(innerElement.getCourseno())
						.courseType(innerElement.getCourseType())
						.subject(subjectModelList)
						.build());
			}
			
			studentModelList.add(StudentModel
					.builder()
					.rollno(element.getRollno())
					.firstname(element.getFirstname())
					.lastname(element.getLastname())
					.address(element.getAddress())
					.age(element.getAge())
					.dob(element.getDob())
					.gender(element.getGender())
					.isStudent(element.isStudent())
					.mobileNumber(element.getMobileNumber())
					.joiningDate(element.getJoiningDate())
					.courses(courseModelList)
					.build());
		}
		return studentModelList;
	}
	
	@Deprecated
//	@Cacheable(value = "courses")
	@Override
	public List<CourseModel> getCourseList() {
		List<Course_v1> courseList = courseRepository.findAll();
		System.err.println(courseList);
		List<CourseModel> courseModelList = new ArrayList<>();
		for(Course_v1 element : courseList) {
			
			List<SubjectModel> subjectModel = new ArrayList<>();
			Set<Subject_v1> subject = element.getSubjects();
			
			for(Subject_v1 innerElement : subject) {
				subjectModel.add(SubjectModel.builder()
						.subjectname(innerElement.getSubjectname())
						.subjectno(innerElement.getSubjectno())
						.textBook(innerElement.getTextBook())
						.build());
			}
			
			courseModelList.add(CourseModel
					.builder()
					.coursename(element.getCoursename())
					.courseno(element.getCourseno())
					.courseType(element.getCourseType())
					.location(element.isLocation())
					.subject(subjectModel)
					.build());
		}
		return courseModelList;
	}
	
	@Deprecated
	@Override
	public Student_v1 assignCoursesToStudents(String rollNo, String courseNo) {
		Student_v1 student = studentRepository.findByRollno(rollNo);
		Course_v1 course = courseRepository.findByCourseno(courseNo);
		
		student.getCourses().add(course);
		Student_v1 savedStudent = studentRepository.save(student);
		return savedStudent;
	}
	
	@Deprecated
	@Override
	public Subject_v1 saveSubject(Subject_v1 subject) {
		return subjectRepository.save(subject);
	}
	
	@Deprecated
	@Override
	public Course_v1 assignSubjectToCourse(String courseNo, String subjectNo) {
		Course_v1 course = courseRepository.findByCourseno(courseNo);
		Subject_v1 subject = subjectRepository.findBySubjectno(subjectNo);
		
		course.getSubjects().add(subject);
		
		Course_v1 savedCourse = courseRepository.save(course);
		return savedCourse;
	}
	
	@Deprecated
	@Transactional
	@Override
	public List<StudentCourseSubject> getStudentCourseSubjectMappings() {
		List<StudentCourseSubject> result = new ArrayList<>();
        List<Student_v1> students = studentRepository.findAll();
        
        for (Student_v1 student : students) {
            if (student.getCourses().isEmpty()) continue;

            for (Course_v1 course : student.getCourses()) {
                if (course.getSubjects().isEmpty()) {
                    // Add a record with no subject details
                    result.add(StudentCourseSubject.builder()
                            .rollno(student.getRollno())
                            .firstname(student.getFirstname())
                            .lastname(student.getLastname())
                            .courseno(course.getCourseno())
                            .coursename(course.getCoursename())
                            .courseType(course.getCourseType())
                            .subjectno(null)
                            .subjectname(null)
                            .textBook(null)
                            .build());
                    continue;
                }

                for (Subject_v1 subject : course.getSubjects()) {
                    StudentCourseSubject dto = StudentCourseSubject.builder()
                            .rollno(student.getRollno())
                            .firstname(student.getFirstname())
                            .lastname(student.getLastname())
                            .courseno(course.getCourseno())
                            .coursename(course.getCoursename())
                            .courseType(course.getCourseType())
                            .subjectno(subject.getSubjectno())
                            .subjectname(subject.getSubjectname())
                            .textBook(subject.getTextBook())
                            .build();

                    result.add(dto);
                }
            }
        }
        return result;
	}
	
	@Deprecated
	@Override
	public List<StudentCourseSubject> getStudentsListBySubject(String subject) {
		List<Student_v1> studentsListBySubject = studentRepository.findAllByCoursesSubjectsSubjectname(subject);
		List<StudentCourseSubject> studentCourseSubjectsList = new ArrayList<>();
		for(Student_v1 element : studentsListBySubject) {
			studentCourseSubjectsList.add(StudentCourseSubject
					.builder()
					.firstname(element.getFirstname())
					.lastname(element.getLastname())
					.subjectname(subject)
					.rollno(element.getRollno())
					.coursename(element.getCourses()
							.stream()
							.map(Course_v1::getCoursename)
							.collect(Collectors.joining(" ")))
					.courseno(element.getCourses()
							.stream()
							.map(Course_v1::getCourseno)
							.collect(Collectors.joining(" ")))
					.subjectno(element.getCourses().stream()
						    .map(course -> course.getSubjects()
						    	.stream()
						    	.filter(subjectNum -> subjectNum.getSubjectname().equalsIgnoreCase(subject))
						        .map(Subject_v1::getSubjectno)
						        .collect(Collectors.joining(" ")))
						    .collect(Collectors.joining(" ")))
					.build());
		}
		
		return studentCourseSubjectsList;
	}
	
	@Deprecated
	@Override
	public List<StudentCourseSubject> getStudentsListBySubjectNo(String subjectno) {
		List<Student_v1> studentsListBySubject = studentRepository.findAllByCoursesSubjectsSubjectno(subjectno);
		List<StudentCourseSubject> studentCourseSubjectsList = new ArrayList<>();
		for(Student_v1 element : studentsListBySubject) {
			studentCourseSubjectsList.add(StudentCourseSubject
					.builder()
					.firstname(element.getFirstname())
					.lastname(element.getLastname())
					.rollno(element.getRollno())
					.coursename(element.getCourses()
							.stream()
							.map(Course_v1::getCoursename)
							.collect(Collectors.joining(" ")))
					.courseno(element.getCourses()
							.stream()
							.map(Course_v1::getCourseno)
							.collect(Collectors.joining(" ")))
					.subjectno(subjectno)
					.build());
		}
		return studentCourseSubjectsList;
	}
	
	@Deprecated
//	@Cacheable(value = "subjects")
	@Override
	public List<SubjectModel> getSubjectList() {
		List<Subject_v1> subjectList = subjectRepository.findAll();
		System.err.println(subjectList);
		List<SubjectModel> subjectModelList = new ArrayList<>();
		for(Subject_v1 element : subjectList) {
			subjectModelList.add(SubjectModel
					.builder()
					.subjectname(element.getSubjectname())
					.subjectno(element.getSubjectno())
					.textBook(element.getTextBook())
					.build());
		}
		return subjectModelList;
	}
	
	@Deprecated
	@Override
	public List<Student_v1> getStudentsListEntity() {
		List<Student_v1> studentsList = studentRepository.findAll();
		return studentsList;
	}
	
	@Deprecated
	@Override
	public List<StudentCourse> getStudentByRollNo(int rollNo) {
		Student_v1 studentModel = studentRepository.findByRollno(String.valueOf(rollNo));
		System.err.println(studentModel);

		List<StudentCourse> studentCourseList = new ArrayList<>();
		List<Course_v1> courseList = new ArrayList<>(studentModel.getCourses());
		
		for(Course_v1 element : courseList) {
			if(element.getSubjects().size() == 0) {
				studentCourseList.add(StudentCourse.builder()
						.rollno(studentModel.getRollno())
						.firstname(studentModel.getFirstname())
						.lastname(studentModel.getLastname())
						.coursename(element.getCoursename())
						.courseno(element.getCourseno())
						.courseType(element.getCourseType())
						.build());
			} else {
				for(Subject_v1 innerElement : element.getSubjects()) {
					studentCourseList.add(StudentCourse.builder()
							.rollno(studentModel.getRollno())
							.firstname(studentModel.getFirstname())
							.lastname(studentModel.getLastname())
							.coursename(element.getCoursename())
							.courseno(element.getCourseno())
							.courseType(element.getCourseType())
							.subject(innerElement.getSubjectname())
							.build());
				}
			}
		}
		return studentCourseList;
	}
}