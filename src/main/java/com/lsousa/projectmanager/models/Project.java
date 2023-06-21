package com.lsousa.projectmanager.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "projects")
public class Project {

	// ========== Primary Key ===================

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// ========== Member Variables ==============

	@NotNull
	@Size(min=1, message="Project title is required")
	private String title;

	@NotNull
	@Size(min=3, message="Project description requires at least 3 characters")
	@Column(length=1000)
	private String description;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future(message="Please set a project due date to the future")
	private Date dueDate;
	
//	@NotNull
//	private User leadUser;

	// ========== Data Creation Trackers ========

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	// ========== Relationships =================

	// One-to-Many (for team lead)

	// Main One
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User leadUser;

	
	
	// One-to-Many (to task)

	// One
	@OneToMany(mappedBy="project", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Task> tasks;

	
	
	// Many-to-many (to users)

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_project", 
        joinColumns = @JoinColumn(name = "project_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

	// ========== Constructors ==================

	// Empty constructor
	public Project() {}

	// ========== Data Creation Event ===========

	@PrePersist
	protected void onCreate() {
	    this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdated() {
	    this.updatedAt = new Date();
	}

	// ========== Getters and Setters ===========

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getLeadUser() {
		return leadUser;
	}

	public void setLeadUser(User leadUser) {
		this.leadUser = leadUser;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
