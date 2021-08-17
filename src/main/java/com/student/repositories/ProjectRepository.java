package com.student.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

	
	

	public List<Project> findAllByStudentIdIn(List<Long> asList);
}
