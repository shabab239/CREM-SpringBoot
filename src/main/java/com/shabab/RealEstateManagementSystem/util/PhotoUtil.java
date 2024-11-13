package com.shabab.RealEstateManagementSystem.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 01/10/2024
 */

@Component
public class PhotoUtil {

    private static String avatarDir;

    @Value("${avatar.dir}")
    public void setAvatarDir(String avatarDir) {
        PhotoUtil.avatarDir = avatarDir;
    }

    public static ApiResponse saveAvatar(MultipartFile avatar) {
        ApiResponse response = new ApiResponse();
        try {
            Path directoryPath = Paths.get(avatarDir);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            String originalFilename = avatar.getOriginalFilename();
            String fileExtension = originalFilename != null ?
                    originalFilename.substring(originalFilename.lastIndexOf('.')) : "";
            String randomFileName = UUID.randomUUID() + fileExtension;
            Path filePath = directoryPath.resolve(randomFileName);

            Files.copy(avatar.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            response.setSuccessful(true);
            response.setData("avatar", randomFileName);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
