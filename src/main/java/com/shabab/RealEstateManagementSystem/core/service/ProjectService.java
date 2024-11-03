package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.construction.Project;
import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import com.shabab.RealEstateManagementSystem.core.model.construction.Floor;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
import com.shabab.RealEstateManagementSystem.core.repository.ProjectRepository;
import com.shabab.RealEstateManagementSystem.core.repository.BuildingRepository;
import com.shabab.RealEstateManagementSystem.core.repository.FloorRepository;
import com.shabab.RealEstateManagementSystem.core.repository.UnitRepository;
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

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private UnitRepository unitRepository;

    // Project methods
    public ApiResponse getProjectById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Project project = projectRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (project == null) {
                return response.error("Project not found");
            }
            response.setData("project", project);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved project");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllProjects() {
        ApiResponse response = new ApiResponse();
        try {
            List<Project> projects = projectRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("projects", projects);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved projects");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse saveProject(Project project) {
        ApiResponse response = new ApiResponse();
        try {
            project.setCompanyId(AuthUtil.getCurrentCompanyId());
            projectRepository.save(project);
            response.setData("project", project);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse updateProject(Project project) {
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
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteProjectById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Project dbProject = projectRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbProject == null) {
                return response.error("Project not found");
            }
            projectRepository.delete(dbProject);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    // Building methods
    public ApiResponse getAllBuildings() {
        ApiResponse response = new ApiResponse();
        try {
            List<Building> buildings = buildingRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("buildings", buildings);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved buildings");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse saveBuilding(Building building) {
        ApiResponse response = new ApiResponse();
        try {
            building.setCompanyId(AuthUtil.getCurrentCompanyId());
            buildingRepository.save(building);
            response.setData("building", building);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse updateBuilding(Building building) {
        ApiResponse response = new ApiResponse();
        try {
            Building dbBuilding = buildingRepository.findByIdAndCompanyId(
                    building.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbBuilding == null) {
                return response.error("Building not found");
            }
            building.setCompanyId(AuthUtil.getCurrentCompanyId());
            buildingRepository.save(building);
            response.setData("building", building);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getBuildingById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Building building = buildingRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (building == null) {
                return response.error("Building not found");
            }
            response.setData("building", building);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved building");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteBuildingById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Building dbBuilding = buildingRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbBuilding == null) {
                return response.error("Building not found");
            }
            buildingRepository.delete(dbBuilding);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    // Floor methods
    public ApiResponse getAllFloors() {
        ApiResponse response = new ApiResponse();
        try {
            List<Floor> floors = floorRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("floors", floors);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved floors");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse saveFloor(Floor floor) {
        ApiResponse response = new ApiResponse();
        try {
            floor.setCompanyId(AuthUtil.getCurrentCompanyId());
            floorRepository.save(floor);
            response.setData("floor", floor);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse updateFloor(Floor floor) {
        ApiResponse response = new ApiResponse();
        try {
            Floor dbFloor = floorRepository.findByIdAndCompanyId(
                    floor.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbFloor == null) {
                return response.error("Floor not found");
            }
            floor.setCompanyId(AuthUtil.getCurrentCompanyId());
            floorRepository.save(floor);
            response.setData("floor", floor);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getFloorById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Floor floor = floorRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (floor == null) {
                return response.error("Floor not found");
            }
            response.setData("floor", floor);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved floor");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteFloorById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Floor dbFloor = floorRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbFloor == null) {
                return response.error("Floor not found");
            }
            floorRepository.delete(dbFloor);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    // Unit methods
    public ApiResponse getAllUnits() {
        ApiResponse response = new ApiResponse();
        try {
            List<Unit> units = unitRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("units", units);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved units");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse saveUnit(Unit unit) {
        ApiResponse response = new ApiResponse();
        try {
            unit.setCompanyId(AuthUtil.getCurrentCompanyId());
            unitRepository.save(unit);
            response.setData("unit", unit);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse updateUnit(Unit unit) {
        ApiResponse response = new ApiResponse();
        try {
            Unit dbUnit = unitRepository.findByIdAndCompanyId(
                    unit.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbUnit == null) {
                return response.error("Unit not found");
            }
            unit.setCompanyId(AuthUtil.getCurrentCompanyId());
            unitRepository.save(unit);
            response.setData("unit", unit);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getUnitById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Unit unit = unitRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (unit == null) {
                return response.error("Unit not found");
            }
            response.setData("unit", unit);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved unit");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteUnitById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Unit dbUnit = unitRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbUnit == null) {
                return response.error("Unit not found");
            }
            unitRepository.delete(dbUnit);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}