package com.shabab.RealEstateManagementSystem.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "const_suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company
}