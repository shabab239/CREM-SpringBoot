package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Task;
import com.shabab.RealEstateManagementSystem.core.model.Worker;
import com.shabab.RealEstateManagementSystem.core.model.WorkerAttendance;
import com.shabab.RealEstateManagementSystem.core.service.TaskService;
import com.shabab.RealEstateManagementSystem.core.service.WorkerService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;

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

}