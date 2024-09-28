package com.shabab.RealEstateManagementSystem.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private Map<String, Object> data;
    private Map<String, String> errors;
    private boolean successful = false;

    public ApiResponse(boolean successful) {
        this.successful = successful;
    }

    public ApiResponse(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public void setData(String key, Object value) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        this.data.put(key, value);
    }

    public ApiResponse success(String message) {
        this.successful = true;
        this.message = message;
        return this;
    }

    public ApiResponse error(String message) {
        this.successful = false;
        this.message = message;
        return this;
    }

    public ApiResponse error(Exception e) {
        this.successful = false;
        try {
            this.message = e.getCause().getCause().getMessage();
        } catch (Exception e1) {
            try {
                this.message = e.getCause().getMessage();
            } catch (Exception e2) {
                this.message = e.getMessage();
            }
        }
        return this;
    }
}

