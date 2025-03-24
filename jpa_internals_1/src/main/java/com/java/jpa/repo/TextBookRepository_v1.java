package com.java.jpa.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jpa.entity.TextBook_v1;

public interface TextBookRepository_v1 extends JpaRepository<TextBook_v1, UUID>{

	TextBook_v1 findByIsbn(String isbn);

}