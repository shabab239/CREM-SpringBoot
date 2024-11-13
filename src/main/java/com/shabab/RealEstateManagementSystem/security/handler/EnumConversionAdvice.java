package com.shabab.RealEstateManagementSystem.security.handler;

import com.shabab.RealEstateManagementSystem.core.model.Task;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 14/11/2024
 */

@ControllerAdvice
public class EnumConversionAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Task.Status.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                String enumValue = text.contains(".") ? text.split("\\.")[1] : text;
                setValue(Task.Status.valueOf(enumValue));
            }
        });
    }
}

