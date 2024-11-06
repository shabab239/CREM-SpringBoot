package com.shabab.RealEstateManagementSystem.account.service;

import com.shabab.RealEstateManagementSystem.account.model.Account;
import com.shabab.RealEstateManagementSystem.account.repository.AccountRepository;
import com.shabab.RealEstateManagementSystem.core.model.rawmaterial.Supplier;
import com.shabab.RealEstateManagementSystem.core.model.worker.Worker;
import com.shabab.RealEstateManagementSystem.core.repository.SupplierRepository;
import com.shabab.RealEstateManagementSystem.core.repository.WorkerRepository;
import com.shabab.RealEstateManagementSystem.security.model.Company;
import com.shabab.RealEstateManagementSystem.security.model.User;
import com.shabab.RealEstateManagementSystem.security.repository.UserRepository;
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
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkerRepository workerRepository;

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

    public Account getCompanyAccount() {
        try {
            Account account = accountRepository.findByCompany_IdAndCompanyId(
                    AuthUtil.getCurrentCompanyId(), AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (account == null) {
                Company company =  AuthUtil.getCurrentCompany();
                account = new Account();
                account.setName(company.getName() + " Company A/C");
                account.setBalance(0.0);
                account.setCompany(company);
                account.setCompanyId(company.getId());
                accountRepository.save(account);
            }
            return account;
        } catch (Exception e) {
            return null;
        }
    }

    public Account getSupplierAccount(Long supplierId) {
        try {
            Account account = accountRepository.findBySupplier_IdAndCompanyId(
                    supplierId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (account == null) {
                Supplier supplier = supplierRepository.findByIdAndCompanyId(
                        supplierId, AuthUtil.getCurrentCompanyId()
                ).orElse(null);
                if (supplier == null) {
                    return null;
                }
                account = new Account();
                account.setName(supplier.getName() + " Company A/C");
                account.setBalance(0.0);
                account.setSupplier(supplier);
                account.setCompanyId(AuthUtil.getCurrentCompanyId());
                accountRepository.save(account);
            }
            return account;
        } catch (Exception e) {
            return null;
        }
    }

    public Account getUserAccount(Long userId) {
        try {
            Account account = accountRepository.findByUser_IdAndCompanyId(
                    userId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (account == null) {
                User user = userRepository.findById(
                        userId, AuthUtil.getCurrentCompanyId()
                ).orElse(null);
                if (user == null) {
                    return null;
                }
                account = new Account();
                account.setName(user.getName() + " Company A/C");
                account.setBalance(0.0);
                account.setUser(user);
                account.setCompanyId(AuthUtil.getCurrentCompanyId());
                accountRepository.save(account);
            }
            return account;
        } catch (Exception e) {
            return null;
        }
    }

    public Account getWorkerAccount(Long workerId) {
        try {
            Account account = accountRepository.findByWorker_IdAndCompanyId(
                    workerId, AuthUtil.getCurrentCompanyId()
            ).orElse(null);
            if (account == null) {
                Worker worker = workerRepository.findByIdAndCompanyId(
                        workerId, AuthUtil.getCurrentCompanyId()
                ).orElse(null);
                if (worker == null) {
                    return null;
                }
                account = new Account();
                account.setName(worker.getName() + " Company A/C");
                account.setBalance(0.0);
                account.setWorker(worker);
                account.setCompanyId(AuthUtil.getCurrentCompanyId());
                accountRepository.save(account);
            }
            return account;
        } catch (Exception e) {
            return null;
        }
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