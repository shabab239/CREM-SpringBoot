package com.shabab.RealEstateManagementSystem.core.model.construction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.RealEstateManagementSystem.core.model.worker.Worker;
import com.shabab.RealEstateManagementSystem.core.model.worker.WorkerAttendance;
import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.RawMaterial;
import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.RawMaterialUsage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

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
@Table(name = "const_stages")
public class ConstructionStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private Date startDate;

    private Date endDate;

    @Enumerated(EnumType.STRING)
    private StageStatus status;

    public enum StageStatus {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED
    }

    @ManyToOne
    private Building building;

    @ManyToOne
    private Floor floor;

    @ManyToOne
    private Unit unit;

    @ManyToMany
    private List<RawMaterial> rawMaterials;

    @ManyToMany
    private List<Worker> workers;

    @JsonIgnore
    @OneToMany(mappedBy = "stage", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<WorkerAttendance> workerAttendances;

    @JsonIgnore
    @OneToMany(mappedBy = "stage", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<RawMaterialUsage> usages;

    @Column(nullable = false)
    private Long companyId; //Loose relation to Company

}
