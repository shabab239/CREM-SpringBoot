package com.shabab.RealEstateManagementSystem.security.config;

import com.shabab.RealEstateManagementSystem.security.filter.CompanyIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${avatar.dir}")
    private String avatarDir;

    @Autowired
    private CompanyIdInterceptor companyIdInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(companyIdInterceptor)
                .addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatar/**")
                .addResourceLocations("file:" + avatarDir + "/");

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
