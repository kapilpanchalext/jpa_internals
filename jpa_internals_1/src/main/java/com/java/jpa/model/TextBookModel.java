package com.java.jpa.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TextBookModel implements Serializable {
	private static final long serialVersionUID = 6795989797639962996L;

	private String isbn;
	private String textbookName;
	private String authorName;
}