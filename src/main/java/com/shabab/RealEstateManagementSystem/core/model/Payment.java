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
 * Created on: 30/09/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "const_payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Paid Amount is required")
    @Column(nullable = false)
    private Double paidAmount;

    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private Date date;

    @ManyToOne
    private User customer;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @NotNull(message = "Payment schedule is required")
    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentSchedule paymentSchedule;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

}