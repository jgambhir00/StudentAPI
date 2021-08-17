package com.student.entities;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.student.utils.Utils;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	
	@Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @Column(name = "access_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date accessTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date updateTime;

    @Column(name = "active")
    boolean active;

    @PrePersist
    public void onUpdate() {
		this.setUpdateTime(Utils.now());
		this.setAccessTime(Utils.now());
    }

    /**
     * TODO: Do more POC on that
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns =
    	@JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<Role>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
