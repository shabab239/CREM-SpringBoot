package com.shabab.RealEstateManagementSystem.core.model;

import com.shabab.RealEstateManagementSystem.security.model.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 30/09/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "const_raw_material_stocks")
public class RawMaterialStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantity is required")
    @Column(nullable = false)
    private Double quantity;

    @NotNull(message = "Last updated date is required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdated;

    @NotNull(message = "Raw material is required")
    @ManyToOne
    @JoinColumn(nullable = false)
    private RawMaterial rawMaterial;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

}