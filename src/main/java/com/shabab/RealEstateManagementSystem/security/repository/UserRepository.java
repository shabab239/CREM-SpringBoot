package com.shabab.RealEstateManagementSystem.security.repository;

import com.shabab.RealEstateManagementSystem.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT u FROM User u
                     WHERE u.company.id = :companyId
            """)
    Optional<List<User>> findAll(@Param("companyId") Long companyId);

    @Query("""
            SELECT u FROM User u
                     WHERE u.id = :userId
                     AND u.company.id = :companyId
            """)
    Optional<User> findById(@Param("userId") Long userId,
                            @Param("companyId") Long companyId);


    @Query("""
            SELECT u FROM User u
                     WHERE u.company.id = :companyId
                     AND u.role = 'ROLE_CUSTOMER'
            """)
    Optional<List<User>> findAllCustomers(@Param("companyId") Long companyId);

}
