package com.shabab.RealEstateManagementSystem.core.model.rawmaterial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
@Table(name = "raw_materials")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "rawMaterial", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<RawMaterialOrder> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "rawMaterial", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<RawMaterialStock> stocks;

    @JsonIgnore
    @OneToMany(mappedBy = "rawMaterial", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<RawMaterialUsage> usages;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company
}