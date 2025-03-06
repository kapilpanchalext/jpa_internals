package com.java.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "courseno", unique = true)
	private String courseno;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "coursename")
	private String coursename;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "subject")
	private String subject;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "course_type")
	private String courseType;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	@Column(name = "location")
	private boolean location;
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
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