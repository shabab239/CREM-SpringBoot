package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Worker;
import com.shabab.RealEstateManagementSystem.core.model.WorkerAttendance;
import com.shabab.RealEstateManagementSystem.core.service.WorkerService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/getAll")
    public ApiResponse getAll() {
        return workerService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestPart Worker worker,
                            @RequestPart(required = false) MultipartFile avatarFile) {
        return workerService.save(worker, avatarFile);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestPart Worker worker,
                              @RequestPart(required = false) MultipartFile avatarFile) {
        return workerService.update(worker, avatarFile);
    }

    @GetMapping("/getById")
    public ApiResponse getById(@RequestParam Long id) {
        return workerService.getById(id);
    }

    @DeleteMapping("/deleteById")
    public ApiResponse deleteById(@RequestParam Long id) {
        return workerService.deleteById(id);
    }

    @PostMapping("/attendance/save")
    public ApiResponse saveAttendance(@Valid @RequestBody WorkerAttendance workerAttendance) {
        return workerService.saveAttendance(workerAttendance);
    }

    @PutMapping("/attendance/update")
    public ApiResponse updateAttendance(@Valid @RequestBody WorkerAttendance workerAttendance) {
        return workerService.updateAttendance(workerAttendance);
    }

    @DeleteMapping("/attendance/deleteById")
    public ApiResponse deleteAttendanceById(@RequestParam Long id) {
        return workerService.deleteAttendanceById(id);
    }

    @GetMapping("/attendance/getById")
    public ApiResponse getAttendanceById(@RequestParam Long id) {
        return workerService.getAttendanceById(id);
    }

    @GetMapping("/attendance/getAll")
    public ApiResponse getAllAttendance() {
        return workerService.getAllAttendance();
    }

    @GetMapping("/attendance/getByWorkerId")
    public ApiResponse getAttendanceByWorkerId(@RequestParam Long workerId) {
        return workerService.getAttendanceByWorkerId(workerId);
    }

    @GetMapping("/attendance/getByDate")
    public ApiResponse getAttendanceByDate(@RequestParam Date date) {
        return workerService.getAttendanceByDate(date);
    }

    @GetMapping("/attendance/getByWorkerIdAndDate")
    public ApiResponse getAttendanceByWorkerIdAndDate(@RequestParam Long workerId, @RequestParam Date date) {
        return workerService.getAttendanceByWorkerIdAndDate(workerId, date);
    }

    @GetMapping("/attendance/getByWorkerIdAndDateRange")
    public ApiResponse getAttendanceByWorkerIdAndDateRange(@RequestParam Long workerId, @RequestParam Date startDate, @RequestParam Date endDate) {
        return workerService.getAttendanceByWorkerIdAndDateRange(workerId, startDate, endDate);
    }

    @GetMapping("/attendance/getByDateRange")
    public ApiResponse getAttendanceByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return workerService.getAttendanceByDateRange(startDate, endDate);
    }

    @GetMapping("/attendance/getByWorkerIdAndDateRangeAndStatus")
    public ApiResponse getAttendanceByWorkerIdAndDateRangeAndStatus(@RequestParam Long workerId, @RequestParam Date startDate, @RequestParam Date endDate, @RequestParam WorkerAttendance.AttendanceStatus status) {
        return workerService.getAttendanceByWorkerIdAndDateRangeAndStatus(workerId, startDate, endDate, status);
    }

    @GetMapping("/attendance/getByDateRangeAndStatus")
    public ApiResponse getAttendanceByDateRangeAndStatus(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam WorkerAttendance.AttendanceStatus status) {
        return workerService.getAttendanceByDateRangeAndStatus(startDate, endDate, status);
    }

    @GetMapping("/attendance/getByWorkerIdAndStatus")
    public ApiResponse getAttendanceByWorkerIdAndStatus(@RequestParam Long workerId, @RequestParam WorkerAttendance.AttendanceStatus status) {
        return workerService.getAttendanceByWorkerIdAndStatus(workerId, status);
    }

    @GetMapping("/attendance/getByStatus")
    public ApiResponse getAttendanceByStatus(@RequestParam WorkerAttendance.AttendanceStatus status) {
        return workerService.getAttendanceByStatus(status);
    }

    @GetMapping("/attendance/getByStatusAndDate")
    public ApiResponse getAttendanceByStatusAndDate(@RequestParam WorkerAttendance.AttendanceStatus status, @RequestParam Date date) {
        return workerService.getAttendanceByStatusAndDate(status, date);
    }

    @GetMapping("/attendance/getByStatusAndDateRange")
    public ApiResponse getAttendanceByStatusAndDateRange(@RequestParam WorkerAttendance.AttendanceStatus status, @RequestParam Date startDate, @RequestParam Date endDate) {
        return workerService.getAttendanceByStatusAndDateRange(status, startDate, endDate);
    }

    @GetMapping("/attendance/getAttendanceByStageIdAndDate")
    public ApiResponse getAttendanceByStageIdAndDate(@RequestParam Long stageId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return workerService.getAttendanceByStageIdAndDate(stageId, date);
    }

    @PostMapping("/attendance/recordAttendance")
    public ApiResponse recordAttendance(@RequestParam Long attendanceId, @RequestParam String attendance) {
        return workerService.recordAttendance(attendanceId, attendance);
    }

}