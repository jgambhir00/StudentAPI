package com.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entities.AssignedProject;

@Repository
public interface AssignedProjectRepository extends JpaRepository<AssignedProject, Integer>{

}
