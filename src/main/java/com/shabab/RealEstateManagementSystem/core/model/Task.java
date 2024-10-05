package com.shabab.RealEstateManagementSystem.core.model;

import com.shabab.RealEstateManagementSystem.security.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 05/10/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "const_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Start date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @NotNull(message = "End date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToMany
    private List<User> employees;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

}
