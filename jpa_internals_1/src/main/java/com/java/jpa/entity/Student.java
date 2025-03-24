package com.java.jpa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Deprecated
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "student", catalog = "jpa_internals")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Student extends BaseData implements Serializable {
	private static final long serialVersionUID = 339912208386100188L;
	
	@Deprecated
	@Column(name = "rollno", unique = true, updatable = false)
	private String rollno;
	
	@Deprecated
	@Column(name = "firstname")
	private String firstname;
	
	@Deprecated
	@Column(name = "lastname")
	private String lastname;
	
	@Deprecated
	@Column(name = "dob")
	private LocalDate dob;
	
	@Deprecated
	@Enumerated
	@Column(name = "gender")
	private Gender gender;
	
	@Deprecated
	@Column(name = "address")
	private String address;
	
	@Deprecated
	@Column(name = "mobileno")
	private long mobileNumber;
	
	@Deprecated
	@Column(name = "age")
	private byte age;
	
	@Deprecated
	@Column(name = "joiningdate")
	private LocalDate joiningDate;
	
	@Deprecated
	@Column(name = "isStudent")
	private boolean isStudent;
	
	@Deprecated
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "student_course", 
        joinColumns = @JoinColumn(name = "student_rollno"), 
        inverseJoinColumns = @JoinColumn(name = "course_no")
    ) 
    private Set<Course> courses = new HashSet<>();
}