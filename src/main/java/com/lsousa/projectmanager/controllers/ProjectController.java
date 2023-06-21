package com.lsousa.projectmanager.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lsousa.projectmanager.models.Project;
import com.lsousa.projectmanager.models.User;
import com.lsousa.projectmanager.services.ProjectService;
import com.lsousa.projectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	
	// ========== Display ========================

    // Get All Route
    @GetMapping("/dashboard")
	public String index(Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		List<Project> projects = projectService.getAllProjects();
		User user = userService.getOneUser((Long) session.getAttribute("userId"));
		List<Project> yourProjects = new ArrayList<Project>();
		List<Project> otherProjects = new ArrayList<Project>();
		for (Project project:projects) {
			if (project.getUsers().contains(user)) {
				yourProjects.add(project);
			} else {
				otherProjects.add(project);
			}
		}
		model.addAttribute("yourProjects", yourProjects);
		model.addAttribute("otherProjects", otherProjects);
		model.addAttribute("userName", user.getFirstName());
		return "dashboard.jsp";
	}

    // Get One Route
    @GetMapping("/projects/{id}")
	public String details(Model model, @PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		model.addAttribute("project", projectService.getOneProject(id));
		return "projdetails.jsp";
	}

    // New Route
    @GetMapping("/projects/new")
    public String newProject(@ModelAttribute("newProject") Project project, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
        return "newproject.jsp";
    }

    // Edit Route
    @GetMapping("/projects/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
    	Project project = projectService.getOneProject(id);
		if(!session.getAttribute("userId").equals(project.getLeadUser().getId())) {
			return "redirect:/dashboard";
		}
		model.addAttribute("editProject", project);
		return "editproject.jsp";
	}
    

// ========== Action =========================

    // POST, PUT, DELETE requests

    // New POST route
    @PostMapping("/projects/new")
	public String create(@Valid @ModelAttribute("newProject") Project project, BindingResult result) {
		
		if (result.hasErrors()) {
			return "newproject.jsp";
		}
		
		List<User> users = new ArrayList<User>();
		
		users.add(project.getLeadUser());
		
		project.setUsers(users);
		projectService.saveProject(project);
		
		return "redirect:/dashboard";
	}
	
    // Edit PUT Route
	@PutMapping("/projects/{id}/edit")
	public String update(@Valid @ModelAttribute("editProject") Project project, @PathVariable("id") Long id, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("project", project);
			return "editproject.jsp";
		}
		List<User> users = projectService.getOneProject(id).getUsers();
		
		project.setUsers(users);
		
		projectService.saveProject(project);
		
		return "redirect:/dashboard";
	}
	
	// Add link
    @PutMapping("/{projectId}/addlink")
    public String addLink(HttpSession session, @PathVariable("projectId") Long projectId) {
    	Long userId = (Long) session.getAttribute("userId");
    	projectService.createUserLink(projectId, userId);
    	return "redirect:/dashboard";
    }

    // Delete Relationship Link Route
    @PutMapping("/{projectId}/leave")
    public String deleteLink(@PathVariable("projectId") Long projectId, HttpSession session) {
        Project project = projectService.getOneProject(projectId);
        User user = userService.getOneUser((Long) session.getAttribute("userId"));
        project.getUsers().remove(user);
        projectService.saveProject(project);
        return "redirect:/dashboard";
    }
}
