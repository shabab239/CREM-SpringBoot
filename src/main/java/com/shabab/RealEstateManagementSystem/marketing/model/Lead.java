package com.shabab.RealEstateManagementSystem.marketing.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Contact Info is required")
    @Column(nullable = false)
    private String contactInfo;

    private String interest;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    private String source;

    @ManyToOne
    private Campaign campaign;

    public enum LeadStatus{
        NEW,
        CONTACTED,
        INTERESTED,
        NOT_INTERESTED,
        CONVERTED,
        LOST
    }

}
