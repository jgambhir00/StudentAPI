package com.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entities.UserRole;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Integer>{
	
	

}
