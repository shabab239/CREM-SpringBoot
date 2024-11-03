package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.RawMaterialStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface RawMaterialStockRepository extends JpaRepository<RawMaterialStock, Long> {

    Optional<List<RawMaterialStock>> findAllByCompanyId(Long companyId);

    Optional<RawMaterialStock> findByIdAndCompanyId(Long id, Long companyId);

    Optional<RawMaterialStock> findByRawMaterialIdAndCompanyId(Long rawMaterialId, Long companyId);

}
