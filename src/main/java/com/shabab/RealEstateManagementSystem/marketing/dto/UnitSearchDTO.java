package com.shabab.RealEstateManagementSystem.marketing.dto;

import com.shabab.RealEstateManagementSystem.core.model.construction.Building;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
import lombok.*;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 24/11/2024
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitSearchDTO {

    private Unit.UnitType type;
    private Long buildingId;
    private Building.BuildingType buildingType;
    private Double minPrice;
    private Double maxPrice;
    private Integer minSize;
    private Integer maxSize;

}
