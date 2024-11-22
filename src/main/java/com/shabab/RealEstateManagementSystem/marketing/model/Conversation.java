package com.shabab.RealEstateManagementSystem.marketing.model;

import com.shabab.RealEstateManagementSystem.security.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 19/11/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.DATE)
    private Date followUpDate;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

}
