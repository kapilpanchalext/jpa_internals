package com.java.restdocs.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.restdocs.model.Greeting;

@RestController
public class HomeController {

	@GetMapping(path = "/")
	public Map<String, Object> greeting(){
		return Collections.singletonMap("message","Hello, World!");
	}
	
	@GetMapping("/greet/{name}")
	public ResponseEntity<Greeting> greet(@PathVariable String name) {
	    return ResponseEntity.ok(new Greeting("Hello, " + name));
	}
	
	@PostMapping(path = "/postContent")
	public Map<String, Object> postMappingExample(@RequestParam String userId, @RequestBody String val){
		return Collections.singletonMap(userId, val);
	}
}
