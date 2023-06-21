package com.lsousa.projectmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsousa.projectmanager.models.Project;
import com.lsousa.projectmanager.models.User;
import com.lsousa.projectmanager.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private UserService userService;
	
	// ========== Create / Update ================

    public Project saveProject(Project m) {
        return projectRepo.save(m);
    }
    
    // ========== Read ===========================

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }
    
    public Project getOneProject(Long id) {
    	return projectRepo.findById(id).orElse(null);
    }
    
    // ========== Delete =========================

    public void deleteOneProject (Long id) {
    	projectRepo.deleteById(id);
    }

    // ========== Relationships ==================
    
    public void createUserLink(Long projectId, Long userId) {
    	Project thisProject = getOneProject(projectId);
    	User thisUser = userService.getOneUser(userId);
    	
    	thisProject.getUsers().add(thisUser);
    	projectRepo.save(thisProject);
    }

}
