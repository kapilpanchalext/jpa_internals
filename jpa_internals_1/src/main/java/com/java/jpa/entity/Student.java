package com.java.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "student", catalog = "jpa_internals")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Student extends BaseData implements Serializable {
	private static final long serialVersionUID = 339912208386100188L;
	
	@Column(name = "rollno", unique = true)
	private String rollno;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "mobileNumber")
	private long mobileNumber;
	
	@Column(name = "age")
	private byte age;
	
	@Column(name = "isStudent")
	private boolean isStudent;
	
	@Column(name = "courseRegisteredIn")
	private int courseRegisteredIn;
	
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