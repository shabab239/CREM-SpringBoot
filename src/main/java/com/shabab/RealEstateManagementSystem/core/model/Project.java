package com.shabab.RealEstateManagementSystem.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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

    private String name;

    private String location;

    private Date startDate;

    private Date endDate;

    private Double budget;

    private ProjectStatus status;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

    public enum ProjectStatus {
        PLANNING,
        IN_PROGRESS,
        COMPLETED
    }
}
