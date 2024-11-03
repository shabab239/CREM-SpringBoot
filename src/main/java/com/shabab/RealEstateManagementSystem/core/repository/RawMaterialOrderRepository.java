package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.RawMaterialOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface RawMaterialOrderRepository extends JpaRepository<RawMaterialOrder, Long> {

    Optional<List<RawMaterialOrder>> findAllByCompanyId(Long companyId);

    Optional<RawMaterialOrder> findByIdAndCompanyId(Long id, Long companyId);

}
