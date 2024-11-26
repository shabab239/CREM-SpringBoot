package com.shabab.RealEstateManagementSystem.marketing.service;

import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import com.shabab.RealEstateManagementSystem.core.model.construction.Floor;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
import com.shabab.RealEstateManagementSystem.core.repository.BuildingRepository;
import com.shabab.RealEstateManagementSystem.core.repository.ProjectRepository;
import com.shabab.RealEstateManagementSystem.core.repository.UnitRepository;
import com.shabab.RealEstateManagementSystem.marketing.dto.UnitSearchDTO;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 24/11/2024
 */

@Service
public class MarketplaceService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ApiResponse getAllAvailableBuildings() {
        ApiResponse response = new ApiResponse();
        try {
            List<Building> buildings = buildingRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            for (Building building : buildings) {
                List<Floor> floors = building.getFloors();
                building.setFloorCount(floors.size());

                int unitCount = floors.stream()
                        .flatMap(floor -> floor.getUnits().stream())
                        .mapToInt(unit -> 1)
                        .sum();

                building.setUnitCount(unitCount);
            }

            response.setData("buildings", buildings);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved available buildings");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getBuildingsByBuildingType(Building.BuildingType type) {
        ApiResponse response = new ApiResponse();
        try {
            List<Building> buildings = buildingRepository.findAllByTypeAndCompanyId(
                            type,
                            AuthUtil.getCurrentCompanyId()
                    )
                    .orElse(new ArrayList<>());

            response.setData("buildings", buildings);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved buildings by building type");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getBuildingsByUnitType(Unit.UnitType type) {
        ApiResponse response = new ApiResponse();
        try {
            List<Building> buildings = buildingRepository.findAllByUnitType(
                            type,
                            AuthUtil.getCurrentCompanyId()
                    )
                    .orElse(new ArrayList<>());

            response.setData("buildings", buildings);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved buildings by building type");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getBuildingWithAvailableUnits(Long buildingId) {
        ApiResponse response = new ApiResponse();
        try {
            Building building = buildingRepository.findByIdAndCompanyId(
                    buildingId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);

            if (building == null) {
                return response.error("Building not found");
            }

            List<Unit> availableUnits = unitRepository.findAllByFloor_BuildingAndStatusAndCompanyId(
                    building,
                    Unit.UnitStatus.AVAILABLE,
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            response.setData("building", building);
            response.setData("availableUnits", availableUnits);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved building details");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getUnitDetails(Long unitId) {
        ApiResponse response = new ApiResponse();
        try {
            Unit unit = unitRepository.findByIdAndCompanyId(
                    unitId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);

            if (unit == null) {
                return response.error("Unit not found");
            }

            response.setData("unit", unit);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved unit details");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse searchAvailableUnits(UnitSearchDTO criteria, int page, int size) {
        ApiResponse response = new ApiResponse();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Specification<Unit> spec = createUnitSpecification(criteria);

            Page<Unit> units = unitRepository.findAll(spec, pageable);

            response.setData("units", units.getContent());
            response.setData("totalPages", units.getTotalPages());
            response.setData("totalElements", units.getTotalElements());
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved units");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getUnitsByBuildingType(Building.BuildingType type) {
        ApiResponse response = new ApiResponse();
        try {
            List<Unit> units = unitRepository.findAllByFloor_Building_TypeAndStatusAndCompanyId(
                            type,
                            Unit.UnitStatus.AVAILABLE,
                            AuthUtil.getCurrentCompanyId()
                    )
                    .orElse(new ArrayList<>());

            response.setData("units", units);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved units by building type");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    private Specification<Unit> createUnitSpecification(UnitSearchDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("companyId"),
                    AuthUtil.getCurrentCompanyId()));

            predicates.add(cb.equal(root.get("status"),
                    Unit.UnitStatus.AVAILABLE));

            if (criteria.getType() != null) {
                predicates.add(cb.equal(root.get("type"),
                        criteria.getType()));
            }

            if (criteria.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("price"),
                        criteria.getMinPrice()));
            }
            if (criteria.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("price"),
                        criteria.getMaxPrice()));
            }

            if (criteria.getMinSize() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("size"),
                        criteria.getMinSize()));
            }
            if (criteria.getMaxSize() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("size"),
                        criteria.getMaxSize()));
            }

            if (criteria.getBuildingType() != null) {
                Join<Unit, Floor> floorJoin = root.join("floor");
                Join<Floor, Building> buildingJoin = floorJoin.join("building");
                predicates.add(cb.equal(buildingJoin.get("type"),
                        criteria.getBuildingType()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}