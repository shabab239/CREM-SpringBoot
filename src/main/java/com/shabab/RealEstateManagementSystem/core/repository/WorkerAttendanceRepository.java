package com.shabab.RealEstateManagementSystem.core.repository;

import com.shabab.RealEstateManagementSystem.core.model.Worker;
import com.shabab.RealEstateManagementSystem.core.model.WorkerAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.sql.Date;
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
}
