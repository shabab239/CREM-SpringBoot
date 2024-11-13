package com.shabab.RealEstateManagementSystem.core.model.construction;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 28/09/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "const_units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private Integer size;

    private Double price;

    @Enumerated(EnumType.STRING)
    private UnitType type;

    @Enumerated(EnumType.STRING)
    private UnitStatus status;

    @NotNull(message = "Floor is required")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Floor floor;

    private String image;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

    public enum UnitType {
        APARTMENT,
        OFFICE,
        SHOP,
        OTHER
    }

    public enum UnitStatus {
        AVAILABLE,
        SOLD,
        RESERVED
    }

}
