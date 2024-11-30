package com.shabab.RealEstateManagementSystem.account.repository;

import com.shabab.RealEstateManagementSystem.account.model.Booking;
import com.shabab.RealEstateManagementSystem.account.model.BookingPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<List<Booking>> findAllByCompanyId(Long companyId);

    Optional<Booking> findByIdAndCompanyId(Long id, Long companyId);

    Optional<Booking> findByUnitIdAndCompanyId(Long unitId, Long companyId);

    Optional<List<Booking>> findAllByCustomerIdAndCompanyId(Long customerId, Long companyId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.companyId = :companyId")
    Optional<Long> countByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT b FROM Booking b WHERE b.customer.id = :customerId")
    Optional<List<Booking>> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT p FROM BookingPayment p WHERE p.customer.id = :customerId")
    Optional<List<BookingPayment>> getPaymentHistory(@Param("customerId") Long customerId);

    @Query("SELECT b FROM Booking b WHERE b.companyId = :companyId ORDER BY b.bookingDate DESC")
    Optional<List<Booking>> findRecentByCompanyId(@Param("companyId") Long companyId);

    @Query(value = """
            SELECT 
            MONTH(b.booking_date) as month,
            COUNT(b.id) as bookings,
            COALESCE(SUM(u.price), 0) as revenue,
            COUNT(DISTINCT l.id) as leads,
            CASE WHEN COUNT(DISTINCT l.id) = 0 THEN 0
            ELSE (COUNT(b.id) * 100.0 / COUNT(DISTINCT l.id)) END as conversion_rate
            FROM acc_bookings b
            JOIN const_units u ON b.unit_id = u.id
            LEFT JOIN leads l ON l.company_id = b.company_id
            WHERE b.company_id = :companyId
            GROUP BY MONTH(b.booking_date)
            LIMIT :size OFFSET :page
            """, nativeQuery = true)
    Optional<List<Map<String, Object>>> getSalesTrends(@Param("companyId") Long companyId,
                                                       @Param("page") int page,
                                                       @Param("size") int size);

    @Query("SELECT SUM(bp.amount) FROM BookingPayment bp " +
            "WHERE bp.date BETWEEN :startDate AND :endDate " +
            "AND bp.companyId = :companyId")
    Optional<Double> sumPaymentsByDateRange(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("companyId") Long companyId
    );
}
