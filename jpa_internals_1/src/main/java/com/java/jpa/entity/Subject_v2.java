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
@Table(name = "subject", catalog = "jpa_internals")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject_v2 extends BaseData_v1 implements Serializable {
	private static final long serialVersionUID = -5308820404851319231L;
	
	@Column(name = "subjectno", unique = true, updatable = false, length = 30)
	private String subjectno;
	
	@Column(name = "subjectname", length = 50)
	private String subjectname;
	
	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
        name = "subject_textbook", 
        joinColumns = @JoinColumn(name = "subject_id"), 
        inverseJoinColumns = @JoinColumn(name = "isbn")
    )
    private Set<TextBook_v1> textbooks = new HashSet<>();
}