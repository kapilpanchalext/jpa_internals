package com.java.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.jpa.entity.Course;
import com.java.jpa.entity.Student;
import com.java.jpa.entity.Subject;
import com.java.jpa.model.StudentCourseSubject;
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
		return null;
	}

	@Override
	public Course findCourseById(String courseno) {
		return courseRepository.findById(Long.valueOf(courseno)).get();
	}

	@Override
	public Student findStudentByRollNo(String rollno) {
		return studentRepository.findByRollno(rollno);
	}

	@Override
	public Course findCourseByCourseNo(String courseno) {
		return courseRepository.findByCourseno(courseno);
	}

	@Override
	public List<Student> getStudentsList() {
		return studentRepository.findAll();
	}

	@Override
	public List<Course> getCourseList() {
		return courseRepository.findAll();
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
                            .subject(course.getSubject())
                            .courseType(course.getCourseType())
                            .subjectno(null) // No subject
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
                            .subject(course.getSubject())
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
		List<Student> studentsListBySubject = studentRepository.findAllById(null);
		return null;
	}
}