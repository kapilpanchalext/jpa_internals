package com.java.jpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
}