package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.Booking;
import com.shabab.RealEstateManagementSystem.account.model.BookingPayment;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.account.repository.BookingRepository;
import com.shabab.RealEstateManagementSystem.account.repository.TransactionRepository;
import com.shabab.RealEstateManagementSystem.core.model.construction.Unit;
import com.shabab.RealEstateManagementSystem.core.repository.ProjectRepository;
import com.shabab.RealEstateManagementSystem.core.repository.UnitRepository;
import com.shabab.RealEstateManagementSystem.core.service.ProjectService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 30/09/2024
 */

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Booking booking = bookingRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (booking == null) {
                return response.error("Booking not found");
            }
            response.setData("booking", booking);
            List<BookingPayment> bookingPayments = booking.getBookingPayments();
            response.setData("bookingPayments", bookingPayments);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved booking and its payments");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Booking> bookings = bookingRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("bookings", bookings);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved bookings");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse save(Booking booking) {
        ApiResponse response = new ApiResponse();
        try {
            Unit unit = unitRepository.findByIdAndCompanyId(
                    booking.getUnit().getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (unit == null) {
                return response.error("Unit not found");
            }
            unit.setStatus(Unit.UnitStatus.RESERVED);
            unitRepository.save(unit);

            booking.setCompanyId(AuthUtil.getCurrentCompanyId());
            bookingRepository.save(booking);

            response.setData("booking", booking);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Booking booking) {
        ApiResponse response = new ApiResponse();
        try {
            Booking dbBooking = bookingRepository.findByIdAndCompanyId(
                    booking.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbBooking == null) {
                return response.error("Booking not found");
            }
            booking.setCompanyId(AuthUtil.getCurrentCompanyId());
            bookingRepository.save(booking);
            response.setData("booking", booking);
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
            Booking dbBooking = bookingRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbBooking == null) {
                return response.error("Booking not found");
            }
            bookingRepository.delete(dbBooking);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllByCustomerId(Long customerId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Booking> bookings = bookingRepository.findAllByCustomerIdAndCompanyId(
                    customerId, AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            List<Unit> units = new ArrayList<>();
            for (Booking booking : bookings) {
                unitRepository.findByIdAndCompanyId(
                        booking.getUnit().getId(), AuthUtil.getCurrentCompanyId()
                ).ifPresent(units::add);
            }
            response.setData("bookings", bookings);
            response.setData("units", units);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved bookings");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByUnitId(Long unitId) {
        ApiResponse response = new ApiResponse();
        try {
            Booking booking = bookingRepository.findByUnitIdAndCompanyId(
                    unitId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (booking == null) {
                return response.error("Booking not found");
            }
            response.setData("booking", booking);
            List<BookingPayment> bookingPayments = booking.getBookingPayments();
            response.setData("bookingPayments", bookingPayments);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved booking");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}