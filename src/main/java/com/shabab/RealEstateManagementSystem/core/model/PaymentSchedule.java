package com.shabab.RealEstateManagementSystem.core.model;

import com.shabab.RealEstateManagementSystem.security.model.Company;
import com.shabab.RealEstateManagementSystem.security.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
@Table(name = "const_pay_schedules")
public class PaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount is required")
    @Column(nullable = false)
    private Double amount;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @NotNull(message = "Construction stage is required")
    @ManyToOne
    @JoinColumn(nullable = false)
    private ConstructionStage constructionStage;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

}
