package com.java.jpa.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class SubjectModel implements Serializable {
	private static final long serialVersionUID = -702876157660835017L;
	
	private String subjectno;
	private String subjectname;
	private String textBook;
}