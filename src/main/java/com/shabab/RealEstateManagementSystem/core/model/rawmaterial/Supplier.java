package com.shabab.RealEstateManagementSystem.core.model.rawmaterial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.RealEstateManagementSystem.account.model.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
@Table(name = "raw_material_suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "supplier", cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<RawMaterialOrder> orders;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company
}