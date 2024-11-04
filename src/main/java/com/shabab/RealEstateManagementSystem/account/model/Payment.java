package com.shabab.RealEstateManagementSystem.account.model;

import com.shabab.RealEstateManagementSystem.security.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
@Table(name = "acc_payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount is required")
    @Column(nullable = false)
    private Double amount;

    @NotNull(message = "Date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String groupTransactionId;

    @NotNull(message = "Booking is required")
    @ManyToOne
    private Booking booking;

    @NotNull(message = "Customer is required")
    @ManyToOne
    private User customer;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

}
