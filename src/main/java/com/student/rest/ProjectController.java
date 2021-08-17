package com.student.rest;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.entities.AssignedProject;
import com.student.entities.Project;
import com.student.entities.Student;
import com.student.repositories.AssignedProjectRepository;
import com.student.repositories.ProjectRepository;
import com.student.repositories.StudentRepository;
import com.student.response.ApiResponse;
import com.student.service.ProjectService;
import com.student.service.StudentService;

@RestController
@RequestMapping("/api/project")
@RolesAllowed("ROLE_ADMIN")
public class ProjectController {
	
	@Autowired
    ProjectService projectService;
	
	@Autowired
    ProjectRepository projectRepository;
	
	@Autowired
    StudentRepository studentRepository;
	
	@Autowired
	AssignedProjectRepository assignedProjectRepository;
	
	@Autowired
    StudentService studentService;
	
	/**
     * TODO: Implement Exception Spring handler
     * 
     * @param project
     * @param studentID
     * @return
     */
	
	@PostMapping("")
    public ResponseEntity<?> create(@RequestBody Project project, @RequestParam("firstName") String firstName,
	    @RequestParam(value = "midName", required = false) String midName,
	    @RequestParam(value = "lastName", required = false) String lastName,
	    @RequestParam(value = "email", required = true) String email,
	    @RequestParam(value = "mobile", required = false) String mobile) {

	ApiResponse response = new ApiResponse();
	HttpStatus httpStatus = null;
	
		try {
		    project = projectService.insert(project);
		    httpStatus = HttpStatus.OK;
		    response.setHttpStatusCode(httpStatus.value());
		    response.setResult(project);
		} catch (Exception e) {
		    httpStatus = HttpStatus.BAD_REQUEST;
		    response.setHttpStatusCode(httpStatus.value());
		    response.setMessage(e.getMessage());
		    e.printStackTrace();
		}
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }
	
	 /**
     * Find student by email, create/update if not exists
     * 
     * @param firstName
     * @param lastName
     * @param mobile
     * @param email
     * @return
     */
    private Student getStudent(String firstName, String lastName, String email) {
		Student student = studentRepository.findAllByEmail(email);
	
		if (student == null)
		    student = new Student();
	
		student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmailId(email);
		
		return studentService.save(student);
    }
    
    @GetMapping("/test")
    public ResponseEntity<?> testAPI() {
    	return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }
     @GetMapping("/assign")
    public ResponseEntity<?> assignProject(@RequestParam("studentID") Integer studentId,
    		@RequestParam("projectId") Integer projectId) {
    	 Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("No ID found"));
    	 Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("No projec foundt"));
    	 AssignedProject assignedProject = new AssignedProject();
    	 assignedProject.setStudent(student);
    	 assignedProject.setProject(project);
    	 
    	 assignedProject = assignedProjectRepository.save(assignedProject);
    	 
    	 return new ResponseEntity<AssignedProject>(assignedProject,HttpStatus.OK);
    }
    
}
