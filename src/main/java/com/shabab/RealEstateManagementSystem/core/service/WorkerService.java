package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.core.model.Worker;
import com.shabab.RealEstateManagementSystem.core.model.WorkerAttendance;
import com.shabab.RealEstateManagementSystem.core.repository.WorkerAttendanceRepository;
import com.shabab.RealEstateManagementSystem.core.repository.WorkerRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/09/2024
 */

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private WorkerAttendanceRepository workerAttendanceRepository;

    public ApiResponse findById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Worker worker = workerRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (worker == null) {
                return response.error("Worker not found");
            }
            response.setData("worker", worker);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Worker> workers = workerRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workers.isEmpty()) {
                return response.error("No workers found");
            }
            response.setData("workers", workers);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved workers");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Worker worker) {
        ApiResponse response = new ApiResponse();
        try {
            worker.setCompanyId(AuthUtil.getCurrentCompanyId());
            workerRepository.save(worker);
            response.setData("worker", worker);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Worker worker) {
        ApiResponse response = new ApiResponse();
        try {
            Worker dbWorker = workerRepository.findByIdAndCompanyId(
                    worker.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbWorker == null) {
                return response.error("Worker not found");
            }
            worker.setCompanyId(AuthUtil.getCurrentCompanyId());
            workerRepository.save(worker);
            response.setData("worker", worker);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Worker dbWorker = workerRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbWorker == null) {
                return response.error("Worker not found");
            }
            workerRepository.delete(dbWorker);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse saveAttendance(WorkerAttendance workerAttendance) {
        ApiResponse response = new ApiResponse();
        try {
            workerAttendance.setCompanyId(AuthUtil.getCurrentCompanyId());
            workerAttendanceRepository.save(workerAttendance);
            response.setData("workerAttendance", workerAttendance);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse updateAttendance(WorkerAttendance workerAttendance) {
        ApiResponse response = new ApiResponse();
        try {
            WorkerAttendance dbWorkerAttendance = workerAttendanceRepository.findByIdAndCompanyId(
                    workerAttendance.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbWorkerAttendance == null) {
                return response.error("Worker attendance not found");
            }
            workerAttendance.setCompanyId(AuthUtil.getCurrentCompanyId());
            workerAttendanceRepository.save(workerAttendance);
            response.setData("workerAttendance", workerAttendance);
            response.setSuccessful(true);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse deleteAttendanceById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            WorkerAttendance dbWorkerAttendance = workerAttendanceRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbWorkerAttendance == null) {
                return response.error("Worker attendance not found");
            }
            workerAttendanceRepository.delete(dbWorkerAttendance);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            WorkerAttendance workerAttendance = workerAttendanceRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (workerAttendance == null) {
                return response.error("Worker attendance not found");
            }
            response.setData("workerAttendance", workerAttendance);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendance");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAllAttendance() {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByWorkerId(Long workerId) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByWorkerIdAndCompanyId(
                    workerId, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByDate(Date date) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByDateAndCompanyId(
                    date, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByWorkerIdAndDate(Long workerId, Date date) {
        ApiResponse response = new ApiResponse();
        try {
            WorkerAttendance workerAttendance = workerAttendanceRepository.findByWorkerIdAndDateAndCompanyId(
                    workerId, date, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (workerAttendance == null) {
                return response.error("Worker attendance not found");
            }
            response.setData("workerAttendance", workerAttendance);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendance");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByWorkerIdAndDateRange(Long workerId, Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByWorkerIdAndDateBetweenAndCompanyId(
                    workerId, startDate, endDate, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByDateRange(Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByDateBetweenAndCompanyId(
                    startDate, endDate, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByWorkerIdAndDateRangeAndStatus(Long workerId, Date startDate, Date endDate,
                                                                     WorkerAttendance.AttendanceStatus status) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByWorkerIdAndDateBetweenAndStatusAndCompanyId(
                    workerId, startDate, endDate, status, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByDateRangeAndStatus(Date startDate, Date endDate,
                                                          WorkerAttendance.AttendanceStatus status) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByDateBetweenAndStatusAndCompanyId(
                    startDate, endDate, status, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByWorkerIdAndStatus(Long workerId, WorkerAttendance.AttendanceStatus status) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByWorkerIdAndStatusAndCompanyId(
                    workerId, status, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByStatus(WorkerAttendance.AttendanceStatus status) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByStatusAndCompanyId(
                    status, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByStatusAndDate(WorkerAttendance.AttendanceStatus status, Date date) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByStatusAndDateAndCompanyId(
                    status, date, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse findAttendanceByStatusAndDateRange(WorkerAttendance.AttendanceStatus status, Date startDate, Date endDate) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByStatusAndDateBetweenAndCompanyId(
                    status, startDate, endDate, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());

            if (workerAttendances.isEmpty()) {
                return response.error("No worker attendances found");
            }
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}