package com.shabab.RealEstateManagementSystem.security.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab-1281539
 * Created on: 25/11/2024
 */

@Component
public class CompanyIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURL = request.getRequestURL().toString();

        if (requestURL.contains("/marketplace") || requestURL.contains("/register")) {
            String host = request.getServerName();

            if ("localhost".equals(host)) {
                request.getSession().setAttribute("companyId", 1L);
            } else if ("abcprops.com".equals(host)) {
                request.getSession().setAttribute("companyId", 2L);
            } else {
                request.getSession().setAttribute("companyId", null);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

