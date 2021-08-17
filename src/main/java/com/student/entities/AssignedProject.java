package com.student.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assigned_project")
public class AssignedProject extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	Student student;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	Project project;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
