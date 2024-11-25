package com.shabab.RealEstateManagementSystem.core.model.construction;

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
 * Author: Shabab
 * Created on: 28/09/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "const_buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BuildingType type;

    @NotNull(message = "Project is required")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Project project;

    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "building", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Floor> floors;

    public enum BuildingType {
        RESIDENTIAL,
        COMMERCIAL,
        MIXED_USE
    }

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

}
