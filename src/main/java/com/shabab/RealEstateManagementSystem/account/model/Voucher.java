package com.shabab.RealEstateManagementSystem.account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
@Table(name = "acc_vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoucherType type;

    @Column(name = "GRP_TR_ID", nullable = false)
    private String groupTransactionId;

    @Column(nullable = false)
    private Integer voucherNo;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(length = 500)
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DR_AC_NO", nullable = false)
    private Account debitAccountNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CR_AC_NO", nullable = false)
    private Account creditAccountNo;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date entryDate;

    @Column(nullable = false)
    private Long companyId; // Loose relation to Company

    public enum VoucherType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        EXPENSE,
        MATERIAL_PURCHASE,
        WORKER_PAYMENT
    }

}
