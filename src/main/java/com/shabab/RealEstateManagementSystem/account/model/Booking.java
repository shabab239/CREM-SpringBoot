package com.shabab.RealEstateManagementSystem.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
import com.shabab.RealEstateManagementSystem.security.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 02/10/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "acc_bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date bookingDate;

    @NotNull(message = "Unit is required")
    @OneToOne
    @JoinColumn(nullable = false)
    private Unit unit;

    @NotNull(message = "Customer is required")
    @ManyToOne
    @JoinColumn(nullable = false)
    private User customer;

    @JsonIgnore
    @OneToMany(mappedBy = "booking", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(nullable = false)
    private List<BookingPayment> bookingPayments;

    @Column(nullable = false)
    private Long companyId; // Loose relation to Company

}