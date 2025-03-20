package com.java.jpa.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CourseModel implements Serializable {
	private static final long serialVersionUID = -2753851427744024762L;

	private String courseno;
	private String coursename;
	private List<SubjectModel> subject;
	private String courseType;
	private boolean location;
}