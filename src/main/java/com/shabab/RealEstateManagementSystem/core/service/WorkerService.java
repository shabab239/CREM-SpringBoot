package com.shabab.RealEstateManagementSystem.core.service;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.model.Transaction;
import com.shabab.RealEstateManagementSystem.account.service.AccountService;
import com.shabab.RealEstateManagementSystem.account.service.TransactionService;
import com.shabab.RealEstateManagementSystem.core.model.construction.ConstructionStage;
import com.shabab.RealEstateManagementSystem.core.model.worker.Worker;
import com.shabab.RealEstateManagementSystem.core.model.worker.WorkerAttendance;
import com.shabab.RealEstateManagementSystem.core.repository.ConstructionStageRepository;
import com.shabab.RealEstateManagementSystem.core.repository.WorkerAttendanceRepository;
import com.shabab.RealEstateManagementSystem.core.repository.WorkerRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import com.shabab.RealEstateManagementSystem.util.PhotoUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Autowired
    private ConstructionStageRepository constructionStageRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    public ApiResponse getById(Long id) {
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

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Worker> workers = workerRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("workers", workers);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved workers");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Worker worker, MultipartFile avatarFile) {
        ApiResponse response = new ApiResponse();
        try {
            if (avatarFile != null) {
                ApiResponse avatarResponse = PhotoUtil.saveAvatar(avatarFile);
                if (!avatarResponse.isSuccessful()) {
                    return avatarResponse;
                }
                worker.setAvatar((String) avatarResponse.getData("avatar"));
            }
            worker.setCompanyId(AuthUtil.getCurrentCompanyId());
            workerRepository.save(worker);

            accountService.getWorkerAccount(worker.getId());

            response.setData("worker", worker);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Worker worker, MultipartFile avatarFile) {
        ApiResponse response = new ApiResponse();
        try {
            Worker dbWorker = workerRepository.findByIdAndCompanyId(
                    worker.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbWorker == null) {
                return response.error("Worker not found");
            }
            if (avatarFile != null) {
                ApiResponse avatarResponse = PhotoUtil.saveAvatar(avatarFile);
                if (!avatarResponse.isSuccessful()) {
                    return avatarResponse;
                }
                dbWorker.setAvatar((String) avatarResponse.getData("avatar"));
            }
            dbWorker.setName(worker.getName());
            dbWorker.setSalary(worker.getSalary());
            dbWorker.setCell(worker.getCell());
            dbWorker.setGender(worker.getGender());
            dbWorker.setAddress(worker.getAddress());
            dbWorker.setJoiningDate(worker.getJoiningDate());
            dbWorker.setJoiningDate(worker.getJoiningDate());
            dbWorker.setCompanyId(AuthUtil.getCurrentCompanyId());
            workerRepository.save(dbWorker);

            accountService.getWorkerAccount(worker.getId());

            response.setData("worker", dbWorker);
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
            //TODO delete worker avatar
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

    public ApiResponse getAttendanceById(Long id) {
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

    public ApiResponse getAllAttendance() {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAttendanceByWorkerId(Long workerId) {
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

    public ApiResponse getAttendanceByDate(LocalDate date) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByDateAndCompanyId(
                    Date.valueOf(date), AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAttendanceByWorkerIdAndDate(Long workerId, Date date) {
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

    public ApiResponse getAttendanceByWorkerIdAndDateRange(Long workerId, Date startDate, Date endDate) {
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

    public ApiResponse getAttendanceByDateRange(Date startDate, Date endDate) {
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

    public ApiResponse getAttendanceByWorkerIdAndDateRangeAndStatus(Long workerId, Date startDate, Date endDate,
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

    public ApiResponse getAttendanceByDateRangeAndStatus(Date startDate, Date endDate,
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

    public ApiResponse getAttendanceByWorkerIdAndStatus(Long workerId, WorkerAttendance.AttendanceStatus status) {
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

    public ApiResponse getAttendanceByStatus(WorkerAttendance.AttendanceStatus status) {
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

    public ApiResponse getAttendanceByStatusAndDate(WorkerAttendance.AttendanceStatus status, Date date) {
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

    public ApiResponse getAttendanceByStatusAndDateRange(WorkerAttendance.AttendanceStatus status, Date startDate, Date endDate) {
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

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse getAttendanceByStageIdAndDate(Long stageId, LocalDate date) {
        ApiResponse response = new ApiResponse();
        try {
            ConstructionStage constructionStage = constructionStageRepository.findByIdAndCompanyId(
                    stageId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (constructionStage == null) {
                return response.error("Construction Stage not found");
            }
            List<Worker> workers = constructionStage.getWorkers();
            if (workers.isEmpty()) {
                return response.error("No workers found in this stage");
            }
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByWorkerListAndDateAndStageIdAndCompanyId(
                    workers, Date.valueOf(date), stageId, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            for (Worker worker : workers) {
                boolean found = false;
                for (WorkerAttendance workerAttendance : workerAttendances) {
                    if (workerAttendance.getWorker().getId().equals(worker.getId()) && workerAttendance.getStage().getId().equals(stageId)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    WorkerAttendance emptyAttendance = new WorkerAttendance();
                    emptyAttendance.setWorker(worker);
                    emptyAttendance.setDate(Date.valueOf(date));
                    emptyAttendance.setStage(constructionStage);
                    emptyAttendance.setCompanyId(AuthUtil.getCurrentCompanyId());
                    workerAttendances.add(emptyAttendance);
                }
            }
            workerAttendanceRepository.saveAll(workerAttendances);
            response.setData("workers", workers);
            response.setData("attendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse recordAttendance(Long attendanceId, String attendance) {
        ApiResponse response = new ApiResponse();
        try {
            WorkerAttendance.AttendanceStatus status = WorkerAttendance.AttendanceStatus.valueOf(attendance);
            WorkerAttendance workerAttendance = workerAttendanceRepository.findByIdAndCompanyId(
                    attendanceId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (workerAttendance == null) {
                return response.error("Worker attendance not found");
            }
            workerAttendance.setStatus(WorkerAttendance.AttendanceStatus.valueOf(attendance));
            workerAttendance.setCompanyId(AuthUtil.getCurrentCompanyId());
            workerAttendanceRepository.save(workerAttendance);
            response.setData("workerAttendance", workerAttendance);
            response.setSuccessful(true);
            response.success("Attendance recorded successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllAttendanceByDateAndCompanyId(Date date) {
        ApiResponse response = new ApiResponse();
        try {
            List<WorkerAttendance> workerAttendances = workerAttendanceRepository.findAllByDateAndCompanyId(
                    date, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("workerAttendances", workerAttendances);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved worker attendances");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse payWorkers(List<Worker> workerList) {
        ApiResponse response = new ApiResponse();
        try {
            for (Worker worker : workerList) {
                String groupTransactionId = TransactionService.generateTransactionId();

                transactionService.recordIncome(
                        worker.getSalary(),
                        "Worker Salary",
                        accountService.getWorkerAccount(worker.getId()),
                        groupTransactionId,
                        Optional.empty()
                );

                transactionService.recordExpense(
                        worker.getSalary(),
                        "Worker Salary",
                        accountService.getCompanyAccount(),
                        groupTransactionId,
                        Optional.empty()
                );
            }
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

}