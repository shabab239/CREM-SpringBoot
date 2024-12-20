package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Task;
import com.shabab.RealEstateManagementSystem.core.service.TaskService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 05/10/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Task task) {
        return taskService.update(task);
    }

    @PostMapping("/markAsCompleted")
    public ApiResponse markAsCompleted(@RequestParam Long id) {
        return taskService.markAsCompleted(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return taskService.deleteById(id);
    }

    @PostMapping("/getAllByStatus")
    public ApiResponse getAllByStatus(@RequestParam Task.Status status) {
        return taskService.getAllByStatus(status);
    }

    @PostMapping("/changeStatus")
    public ApiResponse changeStatus(@RequestParam Long id, @RequestParam Task.Status status) {
        return taskService.changeStatus(id, status);
    }

}