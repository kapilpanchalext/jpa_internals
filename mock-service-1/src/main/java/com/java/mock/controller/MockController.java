package com.java.mock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/response")
public class MockController {

	@GetMapping(path = "/200")
	public ResponseEntity<String> response200String(){
		return ResponseEntity.status(HttpStatus.OK).body("200");
	}
	
	@GetMapping(path = "/500")
	public ResponseEntity<String> response500String(){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).toString());
	}
}
