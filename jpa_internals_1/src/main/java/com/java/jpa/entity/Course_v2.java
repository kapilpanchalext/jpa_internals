package com.java.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	@Column(name = "courseno", unique = true, updatable = false, length = 30)
	private String courseno;

	@Column(name = "coursename", length = 255)
	private String coursename;

	@Column(name = "coursetype", length = 30)
	private String courseType;

	@Column(name = "location", length = 30)
	private String location;

	@Builder.Default
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "course_student",
        joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseno"),
        inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "rollno")
    )
    private Set<Student_v2> students = new HashSet<>();

	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
        name = "course_subject",
        joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseno"),
        inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "subjectno")
    )
    private Set<Subject_v2> subjects = new HashSet<>();
}