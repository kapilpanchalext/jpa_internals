package com.java.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.jpa.entity.Course;
import com.java.jpa.entity.Student;
import com.java.jpa.entity.Subject;
import com.java.jpa.model.CourseModel;
import com.java.jpa.model.StudentCourse;
import com.java.jpa.model.StudentCourseSubject;
import com.java.jpa.model.StudentModel;
import com.java.jpa.model.SubjectModel;
import com.java.jpa.repo.CourseRepository;
import com.java.jpa.repo.StudentRepository;
import com.java.jpa.repo.SubjectRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;	
	private final CourseRepository courseRepository;
	private final SubjectRepository subjectRepository;

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);		
	}

	@Override
	public Student findStudentById(String rollno) {
//		return studentRepository.findByRollno(rollno);
		return null;
	}

	@Override
	public Course findCourseById(String courseno) {
		return courseRepository.findByCourseno(courseno);
	}

	@Override
	public Student findStudentByRollNo(String rollno) {
		return studentRepository.findByRollno(rollno);
	}

	@Override
	public Course findCourseByCourseNo(String courseno) {
//		return courseRepository.findByCourseno(courseno);
		return null;
	}

//	@Cacheable(value = "students", key = "#root.methodName")
	@Override
	public List<StudentModel> getStudentsList() {
		List<Student> studentsList = studentRepository.findAll();
		List<StudentModel> studentModelList = new ArrayList<>();
		for(Student element : studentsList) {
			studentModelList.add(StudentModel
					.builder()
					.rollno(element.getRollno())
					.firstname(element.getFirstname())
					.lastname(element.getLastname())
//					.address(element.getAddress())
					.age(element.getAge())
					.dob(element.getDob())
//					.gender(element.getGender())
//					.isStudent(element.isStudent())
					.mobileNumber(element.getMobileNumber())
					.joiningDate(element.getJoiningDate())
					
					.build());
		}
		return studentModelList;
	}

//	@Cacheable(value = "courses", key = "#root.methodName")
	@Override
	public List<CourseModel> getCourseList() {
		List<Course> courseList = courseRepository.findAll();
		System.err.println(courseList);
		List<CourseModel> courseModelList = new ArrayList<>();
		for(Course element : courseList) {
			
			List<SubjectModel> subjectModel = new ArrayList<>();
			Set<Subject> subject = element.getSubjects();
			
			for(Subject innerElement : subject) {
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

	@Override
	public Student assignCoursesToStudents(String rollNo, String courseNo) {
		Student student = studentRepository.findByRollno(rollNo);
		Course course = courseRepository.findByCourseno(courseNo);
		
		student.getCourses().add(course);
		Student savedStudent = studentRepository.save(student);
		return savedStudent;
	}

	@Override
	public Subject saveSubject(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Override
	public Course assignSubjectToCourse(String courseNo, String subjectNo) {
		Course course = courseRepository.findByCourseno(courseNo);
		Subject subject = subjectRepository.findBySubjectno(subjectNo);
		
		course.getSubjects().add(subject);
		
		Course savedCourse = courseRepository.save(course);
		return savedCourse;
	}

	@Transactional
	@Override
	public List<StudentCourseSubject> getStudentCourseSubjectMappings() {
		List<StudentCourseSubject> result = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        
        for (Student student : students) {
            if (student.getCourses().isEmpty()) continue;

            for (Course course : student.getCourses()) {
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

                for (Subject subject : course.getSubjects()) {
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

	@Override
	public List<StudentCourseSubject> getStudentsListBySubject(String subject) {
		List<Student> studentsListBySubject = studentRepository.findAllByCoursesSubjectsSubjectname(subject);
		List<StudentCourseSubject> studentCourseSubjectsList = new ArrayList<>();
		for(Student element : studentsListBySubject) {
			studentCourseSubjectsList.add(StudentCourseSubject
					.builder()
					.firstname(element.getFirstname())
					.lastname(element.getLastname())
					.subjectname(subject)
					.rollno(element.getRollno())
					.coursename(element.getCourses()
							.stream()
							.map(Course::getCoursename)
							.collect(Collectors.joining(" ")))
					.courseno(element.getCourses()
							.stream()
							.map(Course::getCourseno)
							.collect(Collectors.joining(" ")))
					.subjectno(element.getCourses().stream()
						    .map(course -> course.getSubjects()
						    	.stream()
						    	.filter(subjectNum -> subjectNum.getSubjectname().equalsIgnoreCase(subject))
						        .map(Subject::getSubjectno)
						        .collect(Collectors.joining(" ")))
						    .collect(Collectors.joining(" ")))
					.build());
		}
		
		return studentCourseSubjectsList;
	}

	@Override
	public List<StudentCourseSubject> getStudentsListBySubjectNo(String subjectno) {
		List<Student> studentsListBySubject = studentRepository.findAllByCoursesSubjectsSubjectno(subjectno);
		List<StudentCourseSubject> studentCourseSubjectsList = new ArrayList<>();
		for(Student element : studentsListBySubject) {
			studentCourseSubjectsList.add(StudentCourseSubject
					.builder()
					.firstname(element.getFirstname())
					.lastname(element.getLastname())
					.rollno(element.getRollno())
					.coursename(element.getCourses()
							.stream()
							.map(Course::getCoursename)
							.collect(Collectors.joining(" ")))
					.courseno(element.getCourses()
							.stream()
							.map(Course::getCourseno)
							.collect(Collectors.joining(" ")))
					.subjectno(subjectno)
					.build());
		}
		return studentCourseSubjectsList;
	}

//	@Cacheable(value = "subjects", key = "#root.methodName")
	@Override
	public List<SubjectModel> getSubjectList() {
		List<Subject> subjectList = subjectRepository.findAll();
		System.err.println(subjectList);
		List<SubjectModel> subjectModelList = new ArrayList<>();
		for(Subject element : subjectList) {
			subjectModelList.add(SubjectModel
					.builder()
					.subjectname(element.getSubjectname())
					.subjectno(element.getSubjectno())
					.textBook(element.getTextBook())
					.build());
		}
		return subjectModelList;
	}

	@Override
	public List<Student> getStudentsListEntity() {
		List<Student> studentsList = studentRepository.findAll();
		return studentsList;
	}

	@Override
	public List<StudentCourse> getStudentByRollNo(int rollNo) {
		Student studentModel = studentRepository.findByRollno(String.valueOf(rollNo));
		System.err.println(studentModel);

		List<StudentCourse> studentCourseList = new ArrayList<>();
		List<Course> courseList = new ArrayList<>(studentModel.getCourses());
		
		for(Course element : courseList) {
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
				for(Subject innerElement : element.getSubjects()) {
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