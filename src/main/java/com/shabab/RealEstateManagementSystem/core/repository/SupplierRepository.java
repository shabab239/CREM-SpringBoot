package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.RawMaterialStock;
import com.shabab.RealEstateManagementSystem.core.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<List<Supplier>> findAllByCompanyId(Long companyId);

    Optional<Supplier> findByIdAndCompanyId(Long id, Long companyId);

}
