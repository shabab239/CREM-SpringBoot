package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Project;
import com.shabab.RealEstateManagementSystem.core.repository.ProjectRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Project dbProject = projectRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbProject == null) {
                return response.error("Project not found");
            }
            response.setData("project", dbProject);
            response.setMessage("Successfully retrieved project");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Project> projects = projectRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (projects.isEmpty()) {
                return response.error("No project found");
            }
            response.setData("projects", projects);
            response.setMessage("Successfully retrieved projects");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Project project) {
        ApiResponse response = new ApiResponse();
        try {
            project.setCompanyId(AuthUtil.getCurrentCompanyId());
            projectRepository.save(project);
            response.setData("project", project);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Project project) {
        ApiResponse response = new ApiResponse();
        try {
            Project dbProject = projectRepository.findByIdAndCompanyId(
                    project.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbProject == null) {
                return response.error("Project not found");
            }
            project.setCompanyId(AuthUtil.getCurrentCompanyId());
            projectRepository.save(project);
            response.setData("project", project);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Project dbProject = projectRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbProject == null) {
                return response.error("Project not found");
            }
            projectRepository.delete(dbProject);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}
