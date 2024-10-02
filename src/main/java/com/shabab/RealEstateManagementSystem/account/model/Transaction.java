package com.shabab.RealEstateManagementSystem.account.model;

import com.shabab.RealEstateManagementSystem.core.model.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
@Table(name = "acc_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date transactionDate;

    @Column(nullable = false)
    private String groupTransactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @ManyToOne
    @JoinColumn( nullable = false)
    private Account account;

    @Column(nullable = false)
    private Long companyId; // Loose relation to Company

    public enum TransactionType {
        DEBIT, CREDIT
    }

}