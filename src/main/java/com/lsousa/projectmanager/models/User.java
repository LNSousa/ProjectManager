package com.lsousa.projectmanager.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

	// ========== Primary Key ===================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// ========== Member Variables ==============

	@NotEmpty(message="Please enter a valid first name")
	private String firstName;

	@NotEmpty(message="Please enter a valid last name")
	private String lastName;
	
	@NotEmpty(message="Please enter an email")
	@Email(message="Please enter a valid email address")
	private String email;
	
	@NotEmpty(message="Password is required")
	@Size(min=8, message="Password must be at least 8 characters long")
	private String password;

	@Transient
	@NotEmpty(message="Confirm password is required")
	private String confirm;
	
	// ========== Relationships =================
	
	// Relationship to task
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Task> tasks;
	
	// Relationship to team lead
	
	@OneToMany(mappedBy="leadUser", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Project> projectLeads;
	
	// Relationship to project
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_project", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

	// ========== Constructors ==================

	public User () {}

	// ========== Getters and Setters ===========

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Project> getProjectLeads() {
		return projectLeads;
	}

	public void setProjectLeads(List<Project> projectLeads) {
		this.projectLeads = projectLeads;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
}
