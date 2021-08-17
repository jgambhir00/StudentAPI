package com.student.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

	public enum RoleType {
		ROLE_STUDENT,

		/**
		 * Allows User to manage application
		 */
		ROLE_ADMIN
	}

	@Column(name = "role")
	@Enumerated(EnumType.ORDINAL)
	private RoleType roleType;

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
}
