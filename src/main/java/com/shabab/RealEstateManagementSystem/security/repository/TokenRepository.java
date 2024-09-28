package com.shabab.RealEstateManagementSystem.security.repository;

import com.shabab.RealEstateManagementSystem.security.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */


@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByUsername(String username);

}
