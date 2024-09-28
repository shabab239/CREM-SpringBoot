package com.shabab.RealEstateManagementSystem.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */


public abstract class Constants {

    public static final Map<String, String> GENDERS = new HashMap<String, String>() {{
        put("M", "Male");
        put("F", "Female");
        put("O", "Other");
    }};

    public static final Map<String, String> STATUSES = new HashMap<String, String>() {{
        put("0", "Inactive");
        put("1", "Active");
        put("2", "Suspended");
    }};

    public static final Map<String, String> BLOOD_GROUPS = new HashMap<String, String>() {{
        put("A+", "A Positive");
        put("A-", "A Negative");
        put("B+", "B Positive");
        put("B-", "B Negative");
        put("AB+", "AB Positive");
        put("AB-", "AB Negative");
        put("O+", "O Positive");
        put("O-", "O Negative");
    }};

    public static final Map<String, String> RELIGIONS = new HashMap<String, String>() {{
        put("ISLAM", "Islam");
        put("CHRISTIANITY", "Christianity");
        put("HINDUISM", "Hinduism");
        put("BUDDHISM", "Buddhism");
        put("OTHER", "Other");
    }};

}
