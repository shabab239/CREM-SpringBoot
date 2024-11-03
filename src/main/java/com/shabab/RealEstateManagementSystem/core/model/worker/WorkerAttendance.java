package com.shabab.RealEstateManagementSystem.core.model.worker;

import com.shabab.RealEstateManagementSystem.core.model.construction.ConstructionStage;
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
@Table(name = "worker_attendances")
public class WorkerAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @NotNull(message = "Stage is required")
    @ManyToOne
    @JoinColumn(nullable = false)
    private ConstructionStage stage;

    @NotNull(message = "Worker ID is required")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Worker worker;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

    public enum AttendanceStatus {
        PRESENT,
        ABSENT,
        ON_LEAVE
    }
}