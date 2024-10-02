package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 02/10/2024
 */

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Account account = accountRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (account == null) {
                return response.error("Account not found");
            }
            response.setData("account", account);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved account");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Account> accounts = accountRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("accounts", accounts);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved accounts");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse save(Account account) {
        ApiResponse response = new ApiResponse();
        try {
            account.setCompanyId(AuthUtil.getCurrentCompanyId());
            accountRepository.save(account);
            response.setData("account", account);
            response.setSuccessful(true);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse update(Account account) {
        ApiResponse response = new ApiResponse();
        try {
            Account dbAccount = accountRepository.findByIdAndCompanyId(
                    account.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbAccount == null) {
                return response.error("Account not found");
            }
            account.setCompanyId(AuthUtil.getCurrentCompanyId());
            accountRepository.save(account);
            response.setData("account", account);
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
            Account dbAccount = accountRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (dbAccount == null) {
                return response.error("Account not found");
            }
            accountRepository.delete(dbAccount);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getAllByCompanyId(Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Account> accounts = accountRepository.findAllByCompanyId(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            response.setData("accounts", accounts);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved accounts");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }

    public ApiResponse getByIdAndCompanyId(Long id, Long companyId) {
        ApiResponse response = new ApiResponse();
        try {
            Account account = accountRepository.findByIdAndCompanyId(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (account == null) {
                return response.error("Account not found");
            }
            response.setData("account", account);
            response.setSuccessful(true);
            response.setMessage("Successfully retrieved account");
        } catch (Exception e) {
            return response.error(e);
        }
        return response;
    }
}