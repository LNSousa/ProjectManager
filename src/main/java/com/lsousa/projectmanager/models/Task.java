package com.lsousa.projectmanager.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {

	// ========== Primary Key ===================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// ========== Member Variables ==============
	
	 @NotEmpty
	 @Size(min=3, message="Please add a description to your task")
	 @Column(length=1000)
	 private String description;
	
	// ========== Data Creation Trackers ========
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	// ========== Relationships =================
	
	// One-to-One

	// Other One
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	
	//One-to-Many
	
	// Many
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id")
    private Project project;
	
	// ========== Constructors ==================
	
	// Empty constructor
	public Task() {}
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
