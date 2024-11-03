package com.shabab.RealEstateManagementSystem.core.model.rawmaterial;

import com.shabab.RealEstateManagementSystem.core.model.construction.ConstructionStage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
@Table(name = "raw_material_usage")
public class RawMaterialUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantity is required")
    @Column(nullable = false)
    private Double quantity;

    @Temporal(TemporalType.DATE)
    private Date entryDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private RawMaterial rawMaterial;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ConstructionStage stage;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

}