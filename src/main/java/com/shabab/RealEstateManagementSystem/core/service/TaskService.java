package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Task;
import com.shabab.RealEstateManagementSystem.core.repository.TaskRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 05/10/2024
 */

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Task task = taskRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (task == null) {
                return response.error("Task not found");
            }
            response.setData("task", task);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved task");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Task> tasks = taskRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("tasks", tasks);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved tasks");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }


    public ApiResponse getAllByStatus(Task.Status status) {
        ApiResponse response = new ApiResponse();
        try {
            List<Task> tasks = taskRepository.findAllByStatusAndCompanyId(
                    status, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            response.setData("tasks", tasks);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved tasks");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Task task) {
        ApiResponse response = new ApiResponse();
        try {
            task.setCompanyId(AuthUtil.getCurrentCompanyId());
            taskRepository.save(task);
            response.setData("task", task);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Task task) {
        ApiResponse response = new ApiResponse();
        try {
            Task dbTask = taskRepository.findByIdAndCompanyId(
                    task.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbTask == null) {
                return response.error("Task not found");
            }
            dbTask.setDescription(task.getDescription());
            dbTask.setStartDate(task.getStartDate());
            dbTask.setEndDate(task.getEndDate());
            dbTask.setStatus(task.getStatus());
            taskRepository.save(dbTask);
            response.setData("task", dbTask);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Task dbTask = taskRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbTask == null) {
                return response.error("Task not found");
            }
            taskRepository.delete(dbTask);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse markAsCompleted(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Task dbTask = taskRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbTask == null) {
                return response.error("Task not found");
            }
            dbTask.setStatus(Task.Status.COMPLETED);
            taskRepository.save(dbTask);
            response.setSuccessful(true);
            response.success("Marked as Completed");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse changeStatus(Long id, Task.Status status) {
        ApiResponse response = new ApiResponse();
        try {
            Task dbTask = taskRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbTask == null) {
                return response.error("Task not found");
            }
            dbTask.setStatus(status);
            taskRepository.save(dbTask);
            response.setSuccessful(true);
            response.success("Status updated successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}