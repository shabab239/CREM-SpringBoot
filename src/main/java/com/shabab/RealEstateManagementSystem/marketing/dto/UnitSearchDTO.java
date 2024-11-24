package com.shabab.RealEstateManagementSystem.marketing.dto;

import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 24/11/2024
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitSearchDTO {

    private Unit.UnitType type;
    private Building.BuildingType buildingType;
    private Double minPrice;
    private Double maxPrice;
    private Integer minSize;
    private Integer maxSize;

}
