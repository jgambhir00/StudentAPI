package com.student.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entities.Project;
import com.student.repositories.ProjectRepository;



@RestController
@RequestMapping("/api/project")
public class ProjectService {
	
	
	@Autowired
    private ProjectRepository projectRepository;
	
	public Project insert(Project project) {
		project = projectRepository.save(project);
	
	    return project;
	}
	
	/**
     * Get All project List.
     * 
     * @return
     */
	public List<Project> getAll() {
		return projectRepository.findAll();
	    }
	/**
     * Get list of project done by student
     * 
     * @param studentID
     * @return
     */
	public List<Project> findByStudentId(Long studentID) {
		return projectRepository.findAllByStudentIdIn(Arrays.asList(studentID));
	    }
}
