package com.student.rest;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.student.entities.Student;
import com.student.repositories.StudentRepository;

@Controller
@RequestMapping("/api/student")
@RolesAllowed("ROLE_ADMIN")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Student student) {
		return new ResponseEntity<Student>(studentRepository.save(student), HttpStatus.OK);
	}
}
