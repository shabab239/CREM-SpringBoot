package com.shabab.RealEstateManagementSystem.security.service;

import com.shabab.RealEstateManagementSystem.security.model.Company;
import com.shabab.RealEstateManagementSystem.security.repository.CompanyRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public ApiResponse getAll() {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            List<Company> companyList = companyRepository.findAll();
            apiResponse.setData("companyList", companyList);
            apiResponse.setSuccessful(true);
            return apiResponse;
        } catch (Exception e) {
            return apiResponse.error(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Company company = companyRepository.findById(id).orElse(null);
            if (company == null) {
                return apiResponse.error("Company not found");
            }
            apiResponse.setData("company", company);
            apiResponse.setSuccessful(true);
            return apiResponse;
        } catch (Exception e) {
            return apiResponse.error(e);
        }
    }

    public ApiResponse save(Company company) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            companyRepository.save(company);
            apiResponse.setSuccessful(true);
            apiResponse.setMessage("Company saved successfully");
            return apiResponse;
        } catch (Exception e) {
            return apiResponse.error(e);
        }
    }

    public ApiResponse update(Company company) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Company dbCompany = companyRepository.findById(company.getId()).orElse(null);
            if (dbCompany == null) {
                return apiResponse.error("Company not found");
            }
            companyRepository.save(company);
            apiResponse.setSuccessful(true);
            apiResponse.setMessage("Company updated successfully");
            return apiResponse;
        } catch (Exception e) {
            return apiResponse.error(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Company dbCompany = companyRepository.findById(id).orElse(null);
            if (dbCompany == null) {
                return apiResponse.error("Company not found");
            }
            companyRepository.deleteById(id);
            apiResponse.setSuccessful(true);
            apiResponse.setMessage("Company deleted successfully");
            return apiResponse;
        } catch (Exception e) {
            return apiResponse.error(e);
        }
    }
}


