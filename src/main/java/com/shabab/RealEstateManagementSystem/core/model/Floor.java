package com.shabab.RealEstateManagementSystem.core.model;

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
 * Created on: 28/09/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "core_floors")
public class Floor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NAME name;

    @NotNull(message = "Building is required")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Building building;

    @JsonIgnore
    @OneToMany(mappedBy = "floor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Unit> units;

    public enum NAME {
        FIRST,
        SECOND,
        THIRD,
        FOURTH,
        FIFTH,
        SIXTH,
        SEVENTH,
        EIGHTH,
        NINTH,
        TENTH,
        ELEVENTH,
        TWELVETH,
        THIRTEENTH,
        FOURTEENTH,
        FIFTEENTH
    }

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

}
