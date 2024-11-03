package com.shabab.RealEstateManagementSystem.security.controller;

import com.shabab.RealEstateManagementSystem.security.model.Token;
import com.shabab.RealEstateManagementSystem.security.service.AuthService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */


@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody Token token) {
        return authService.authenticate(token);
    }

}
