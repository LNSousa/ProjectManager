package com.lsousa.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lsousa.projectmanager.models.Task;
import com.lsousa.projectmanager.services.ProjectService;
import com.lsousa.projectmanager.services.TaskService;
import com.lsousa.projectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TaskController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/projects/{id}/tasks")
	public String projectTasks(@PathVariable("id") Long id, Model model, @ModelAttribute("newTask") Task task) {
		model.addAttribute("project", projectService.getOneProject(id));
		model.addAttribute("tasks", projectService.getOneProject(id).getTasks());
		
		return "tasks.jsp";
	}
	
	@PostMapping("/projects/tasks/new")
	public String createTask(@Valid @ModelAttribute("newTask") Task task, BindingResult result, Model model, HttpSession session) {
		model.addAttribute("project", projectService.getOneProject(task.getProject().getId()));
		model.addAttribute("tasks", projectService.getOneProject(task.getProject().getId()).getTasks());
		if (result.hasErrors()) {
			return "tasks.jsp";
		}
		
		task.setUser(userService.getOneUser((Long) session.getAttribute("userId")));
		
		taskService.saveTask(task);
		
		return "redirect:/projects/" + task.getProject().getId() + "/tasks";
	}
}
