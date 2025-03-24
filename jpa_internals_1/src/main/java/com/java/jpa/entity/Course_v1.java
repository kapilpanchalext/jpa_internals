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
import lombok.RequiredArgsConstructor;

@Deprecated
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "course", catalog = "jpa_internals")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Course_v1 extends BaseData_v1 implements Serializable {
	private static final long serialVersionUID = -331452226800855670L;
	
	@Deprecated
	@Column(name = "courseno", unique = true)
	private String courseno;
	
	@Deprecated
	@Column(name = "coursename")
	private String coursename;
	
	@Deprecated
	@Column(name = "course_type")
	private String courseType;
	
	@Deprecated
	@Column(name = "location")
	private boolean location;
	
	@Deprecated
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "course_subject", 
        joinColumns = @JoinColumn(name = "course_no"), 
        inverseJoinColumns = @JoinColumn(name = "subject_no")
    )
    private Set<Subject_v1> subjects = new HashSet<>();
}