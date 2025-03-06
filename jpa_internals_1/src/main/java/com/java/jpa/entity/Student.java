package com.java.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "rollno", unique = true)
	private String rollno;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "firstname")
	private String firstname;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "lastname")
	private String lastname;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "address")
	private String address;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "mobileNumber")
	private long mobileNumber;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "age")
	private byte age;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "isStudent")
	private boolean isStudent;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "courseRegisteredIn")
	private int courseRegisteredIn;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
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