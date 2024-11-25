package com.shabab.RealEstateManagementSystem.util;

import com.shabab.RealEstateManagementSystem.security.model.Company;
import com.shabab.RealEstateManagementSystem.security.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */


public class AuthUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public static Company getCurrentCompany() {
        return getCurrentUser().getCompany();
    }

    public static Long getCurrentCompanyId() {
        try {
            Company company = getCurrentCompany();
            return company.getId();
        } catch (Exception e) {
            return getCompanyIdFromSession(); //fallback
        }

    }

    private static HttpSession getCurrentSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getSession(false);
        }
        return null;
    }

    public static Long getCompanyIdFromSession() {
        HttpSession session = getCurrentSession();
        if (session != null) {
            Object companyId = session.getAttribute("companyId");
            if (companyId instanceof Long) {
                return (Long) companyId;
            }
        }
        return null;
    }


}
