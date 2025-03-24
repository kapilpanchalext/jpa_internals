package com.java.jpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "textbook", catalog = "jpa_internals")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TextBook_v1 extends BaseData_v1 implements Serializable {
	private static final long serialVersionUID = 876348942963178298L;

	@Column(name = "isbn", unique = true, updatable = false, length = 30)
	private String isbn;
	
	@Column(name = "textbookname", length = 255)
	private String textbookName;
	
	@Column(name = "authorname", length = 100)
	private String authorName;
}