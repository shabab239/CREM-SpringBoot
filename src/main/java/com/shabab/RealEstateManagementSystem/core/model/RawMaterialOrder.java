package com.shabab.RealEstateManagementSystem.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 30/09/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "const_raw_material_orders")
public class RawMaterialOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantity is required")
    @Column(nullable = false)
    private Double quantity;

    @NotNull(message = "Unit price is required")
    @Column(nullable = false)
    private Double unitPrice;

    @NotNull(message = "Order date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @NotNull(message = "Total price is required")
    @Column(nullable = false)
    private Double totalPrice;

    @NotNull(message = "Status is required")
    @Column(nullable = false)
    private RawMaterialOrderStatus status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private RawMaterial rawMaterial;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Supplier supplier;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

    public enum RawMaterialOrderStatus {
        PENDING,
        DELIVERED,
        CANCELLED
    }
}