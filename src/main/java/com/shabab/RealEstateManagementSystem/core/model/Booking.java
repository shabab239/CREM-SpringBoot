package com.shabab.RealEstateManagementSystem.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "const_bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date bookingDate;

    @NotNull(message = "Amount is required")
    @Column(nullable = false)
    private Double amount;

    @OneToOne
    @JoinColumn(nullable = false)
    private Unit unit;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User customer;

    @JsonIgnore
    @OneToMany(mappedBy = "booking", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(nullable = false)
    private List<Payment> payments;

    @Column(nullable = false)
    private Long companyId; // Loose relation to Company

}