package com.lsousa.projectmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsousa.projectmanager.models.Task;
import com.lsousa.projectmanager.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepo;
	
	// ========== Create / Update ================

    public Task saveTask(Task m) {
        return taskRepo.save(m);
    }

    // ========== Read ===========================

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getOneTask (Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    // ========== Delete =========================

    public void deleteOneTask (Long id) {
    	taskRepo.deleteById(id);
    }
}
