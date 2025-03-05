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

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "course", catalog = "jpa_internals")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Course extends BaseData implements Serializable {
	private static final long serialVersionUID = -331452226800855670L;
	
	@Column(name = "courseno", unique = true)
	private String courseno;
	
	@Column(name = "coursename")
	private String coursename;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "course_type")
	private String courseType;
	
	@Column(name = "location")
	private boolean location;
	
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "course_subject", 
        joinColumns = @JoinColumn(name = "course_no"), 
        inverseJoinColumns = @JoinColumn(name = "subject_no")
    )
    private Set<Subject> subjects = new HashSet<>();
}