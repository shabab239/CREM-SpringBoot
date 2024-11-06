package com.shabab.RealEstateManagementSystem.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.Supplier;
import com.shabab.RealEstateManagementSystem.core.model.worker.Worker;
import com.shabab.RealEstateManagementSystem.security.model.Company;
import com.shabab.RealEstateManagementSystem.security.model.User;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
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

    @Column(nullable = false, columnDefinition = "default 0.0")
    private Double balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company; // One company account

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @Column(nullable = false)
    private Long companyId; // Loose relation to Company


}