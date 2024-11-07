package com.shabab.RealEstateManagementSystem.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.RealEstateManagementSystem.account.model.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sec_companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Cell number is required")
    @Pattern(regexp = "^\\d{11}$", message = "Cell number must be 11 digits")
    @Column(length = 11, nullable = false, unique = true)
    private String contact;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String address;

    @JsonIgnore
    @OneToOne(mappedBy = "company", cascade = CascadeType.REMOVE)
    private Account account;

    public Company(Long id) {
        this.id = id;
    }
}
