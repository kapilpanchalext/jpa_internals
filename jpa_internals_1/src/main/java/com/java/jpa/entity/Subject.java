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
@Table(name = "subject", catalog = "jpa_internals")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Subject extends BaseData implements Serializable {
	private static final long serialVersionUID = -1202970504720736463L;

	@Column(name = "subjectno", unique = true)
	private String subjectno;

	@Column(name = "subject_name")
	private String subjectname;

	@Column(name = "textbook")
	private String textBook;	
}