package com.java.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "course", catalog = "jpa_internals")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course_v2 extends BaseData_v1 implements Serializable {
	private static final long serialVersionUID = -222529697388266058L;

	@Column(name = "courseno", unique = true, updatable = false)
	private String courseno;
	
	@Column(name = "coursename")
	private String coursename;
	
	@Column(name = "course_type")
	private String courseType;
	
	@Column(name = "location")
	private String location;
	
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "course_student", 
        joinColumns = @JoinColumn(name = "rollno"), 
        inverseJoinColumns = @JoinColumn(name = "course_no")
    )
    private Set<Student_v2> students = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "course_subject", 
        joinColumns = @JoinColumn(name = "course_no"), 
        inverseJoinColumns = @JoinColumn(name = "subject_no")
    )
    private Set<Subject_v2> subjects = new HashSet<>();
}