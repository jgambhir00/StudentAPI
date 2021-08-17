package com.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student , Integer>{
	
	public Student findAllByEmail(String emailId);
	
}
