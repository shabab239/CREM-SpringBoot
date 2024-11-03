package com.shabab.RealEstateManagementSystem.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "acc_accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private Double balance;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @Column(nullable = false)
    private Long companyId; // Loose relation to Company
}