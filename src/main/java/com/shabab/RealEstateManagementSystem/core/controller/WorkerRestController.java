package com.shabab.RealEstateManagementSystem.core.controller;

import com.shabab.RealEstateManagementSystem.core.model.Worker;
import com.shabab.RealEstateManagementSystem.core.model.WorkerAttendance;
import com.shabab.RealEstateManagementSystem.core.service.WorkerService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/worker")
public class WorkerRestController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/")
    public ApiResponse findAll() {
        return workerService.findAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Worker worker) {
        return workerService.save(worker);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Worker worker) {
        return workerService.update(worker);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return workerService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
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

    @DeleteMapping("/attendance/{id}")
    public ApiResponse deleteAttendanceById(@PathVariable Long id) {
        return workerService.deleteAttendanceById(id);
    }

    @GetMapping("/attendance/{id}")
    public ApiResponse findAttendanceById(@PathVariable Long id) {
        return workerService.findAttendanceById(id);
    }

    @GetMapping("/attendance")
    public ApiResponse findAllAttendance() {
        return workerService.findAllAttendance();
    }

    @GetMapping("/attendance/worker/{workerId}")
    public ApiResponse findAttendanceByWorkerId(@PathVariable Long workerId) {
        return workerService.findAttendanceByWorkerId(workerId);
    }

    @GetMapping("/attendance/date")
    public ApiResponse findAttendanceByDate(@RequestParam Date date) {
        return workerService.findAttendanceByDate(date);
    }

    @GetMapping("/attendance/worker/{workerId}/date")
    public ApiResponse findAttendanceByWorkerIdAndDate(@PathVariable Long workerId, @RequestParam Date date) {
        return workerService.findAttendanceByWorkerIdAndDate(workerId, date);
    }

    @GetMapping("/attendance/worker/{workerId}/date-range")
    public ApiResponse findAttendanceByWorkerIdAndDateRange(@PathVariable Long workerId, @RequestParam Date startDate, @RequestParam Date endDate) {
        return workerService.findAttendanceByWorkerIdAndDateRange(workerId, startDate, endDate);
    }

    @GetMapping("/attendance")
    public ApiResponse findAttendanceByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return workerService.findAttendanceByDateRange(startDate, endDate);
    }

    @GetMapping("/attendance/worker")
    public ApiResponse findAttendanceByWorkerIdAndDateRangeAndStatus(@RequestParam Long workerId, @RequestParam Date startDate, @RequestParam Date endDate, @RequestParam WorkerAttendance.AttendanceStatus status) {
        return workerService.findAttendanceByWorkerIdAndDateRangeAndStatus(workerId, startDate, endDate, status);
    }

    @GetMapping("/attendance/date")
    public ApiResponse findAttendanceByDateRangeAndStatus(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam WorkerAttendance.AttendanceStatus status) {
        return workerService.findAttendanceByDateRangeAndStatus(startDate, endDate, status);
    }

    @GetMapping("/attendance/worker/status")
    public ApiResponse findAttendanceByWorkerIdAndStatus(@RequestParam Long workerId, @RequestParam WorkerAttendance.AttendanceStatus status) {
        return workerService.findAttendanceByWorkerIdAndStatus(workerId, status);
    }

    @GetMapping("/attendance/status")
    public ApiResponse findAttendanceByStatus(@RequestParam WorkerAttendance.AttendanceStatus status) {
        return workerService.findAttendanceByStatus(status);
    }

    @GetMapping("/attendance/status/date")
    public ApiResponse findAttendanceByStatusAndDate(@RequestParam WorkerAttendance.AttendanceStatus status, @RequestParam Date date) {
        return workerService.findAttendanceByStatusAndDate(status, date);
    }

    @GetMapping("/attendance/status/date-range")
    public ApiResponse findAttendanceByStatusAndDateRange(@RequestParam WorkerAttendance.AttendanceStatus status, @RequestParam Date startDate, @RequestParam Date endDate) {
        return workerService.findAttendanceByStatusAndDateRange(status, startDate, endDate);
    }
}