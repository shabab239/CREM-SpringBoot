package com.shabab.RealEstateManagementSystem.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.RealEstateManagementSystem.security.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "core_projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    private String location;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private Double budget;

    @NotNull(message = "Project Status is required")
    @Column(nullable = false)
    private ProjectStatus status;

    @ManyToOne
    private User manager;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Building> buildings;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

    public enum ProjectStatus {
        PLANNING,
        IN_PROGRESS,
        COMPLETED
    }
}
