package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.RawMaterialOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
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

    @Query("SELECT SUM(ro.totalPrice) FROM RawMaterialOrder ro " +
            "WHERE ro.orderDate BETWEEN :startDate AND :endDate " +
            "AND ro.status = :status " +
            "AND ro.companyId = :companyId")
    Optional<Double> sumTotalPriceByDateRange(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("status") RawMaterialOrder.RawMaterialOrderStatus status,
            @Param("companyId") Long companyId
    );

}
