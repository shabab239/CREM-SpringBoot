package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.worker.Worker;
import com.shabab.RealEstateManagementSystem.core.model.worker.WorkerAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

public interface WorkerAttendanceRepository extends JpaRepository<WorkerAttendance, Long> {

    Optional<List<WorkerAttendance>> findAllByCompanyId(Long companyId);

    Optional<WorkerAttendance> findByIdAndCompanyId(Long id, Long companyId);

    Optional<List<WorkerAttendance>> findAllByWorkerIdAndCompanyId(Long workerId, Long companyId);

    Optional<WorkerAttendance> findByWorkerIdAndDateAndCompanyId(Long workerId, Date date, Long companyId);

    Optional<List<WorkerAttendance>> findAllByWorkerIdAndDateBetweenAndCompanyId(Long workerId, Date startDate, Date endDate, Long companyId);

    Optional<List<WorkerAttendance>> findAllByDateBetweenAndCompanyId(Date startDate, Date endDate, Long companyId);

    Optional<List<WorkerAttendance>> findAllByDateAndCompanyId(Date date, Long companyId);

    Optional<List<WorkerAttendance>> findAllByWorkerIdAndDateBetweenAndStatusAndCompanyId(Long workerId, Date startDate, Date endDate, WorkerAttendance.AttendanceStatus status, Long companyId);

    Optional<List<WorkerAttendance>> findAllByDateBetweenAndStatusAndCompanyId(Date startDate, Date endDate, WorkerAttendance.AttendanceStatus status, Long companyId);

    Optional<List<WorkerAttendance>> findAllByWorkerIdAndStatusAndCompanyId(Long workerId, WorkerAttendance.AttendanceStatus status, Long companyId);

    Optional<List<WorkerAttendance>> findAllByStatusAndCompanyId(WorkerAttendance.AttendanceStatus status, Long companyId);

    Optional<List<WorkerAttendance>> findAllByStatusAndDateAndCompanyId(WorkerAttendance.AttendanceStatus status, Date date, Long companyId);

    Optional<List<WorkerAttendance>> findAllByStatusAndDateBetweenAndCompanyId(WorkerAttendance.AttendanceStatus status, Date startDate, Date endDate, Long companyId);

    Optional<List<WorkerAttendance>> findAllByStageIdAndDateAndCompanyId(Long stageId, Date date, Long companyId);

    @Query("""
                        SELECT wa
                        FROM WorkerAttendance wa
                        WHERE wa.worker IN :workerList
                        AND wa.date = :date
                        AND wa.companyId = :companyId
            """)
    Optional<List<WorkerAttendance>> findAllByWorkerListAndDateAndCompanyId(List<Worker> workerList, Date date, Long companyId);

    @Query("""
                        SELECT wa
                        FROM WorkerAttendance wa
                        WHERE wa.worker IN :workerList
                        AND wa.date = :date
                        AND wa.stage.id = :stageId
                        AND wa.companyId = :companyId
            """)
    Optional<List<WorkerAttendance>> findAllByWorkerListAndDateAndStageIdAndCompanyId(List<Worker> workerList, Date date, Long stageId, Long companyId);

    @Query("SELECT a FROM WorkerAttendance a " +
            "WHERE a.stage.building.project.id = :projectId " +
            "AND a.date BETWEEN :startDate AND :endDate " +
            "AND a.companyId = :companyId")
    Optional<List<WorkerAttendance>> findByProjectIdAndDateBetweenAndCompanyId(
            @Param("projectId") Long projectId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("companyId") Long companyId
    );
}
