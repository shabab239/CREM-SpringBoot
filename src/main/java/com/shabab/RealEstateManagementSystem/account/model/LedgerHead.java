package com.shabab.RealEstateManagementSystem.account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 04/11/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "acc_ledger_heads")
public class LedgerHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @Size(min = 3, max = 50, message = "3-50 Characters Required")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LedgerHeadType type;

    @Column(nullable = false)
    private Long companyId; // Loose relation to Company

    public enum LedgerHeadType {
        ASSET, LIABILITY, INCOME, EXPENSE
    }

}
