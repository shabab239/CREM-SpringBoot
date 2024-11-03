package com.shabab.RealEstateManagementSystem.security.controller;

import com.shabab.RealEstateManagementSystem.security.model.User;
import com.shabab.RealEstateManagementSystem.security.service.UserService;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestPart("user") User user,
                            @RequestPart(value = "avatar", required = false) MultipartFile avatar) {
        return userService.save(user, avatar);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestPart("user") User user,
                              @RequestPart(value = "avatar", required = false) MultipartFile avatar) {
        return userService.update(user, avatar);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/customer/{id}")
    public ApiResponse getCustomerById(@PathVariable Long id) {
        return userService.getCustomerById(id);
    }

    @GetMapping("/customers")
    public ApiResponse getAllCustomers() {
        return userService.getAllCustomers();
    }

}

