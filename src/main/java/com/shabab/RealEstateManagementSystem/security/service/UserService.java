package com.shabab.RealEstateManagementSystem.security.service;

import com.shabab.RealEstateManagementSystem.security.model.Token;
import com.shabab.RealEstateManagementSystem.security.model.User;
import com.shabab.RealEstateManagementSystem.security.repository.TokenRepository;
import com.shabab.RealEstateManagementSystem.security.repository.UserRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import com.shabab.RealEstateManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */

@Service
public class UserService implements UserDetailsService {

    @Value("${avatar.dir}")
    private String avatarDir;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<User> users = userRepository.findAll(
                    AuthUtil.getCurrentCompanyId()
            ).orElse(new ArrayList<>());
            if (users.isEmpty()) {
                return response.error("No user found");
            }
            response.setData("users", users);
            response.setSuccessful(true);
            response.success("Successfully retrieved all users");
            return response;
        } catch (Exception e) {
            return response.error(e);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse save(User user, MultipartFile avatar) {
        ApiResponse response = new ApiResponse();
        try {
            if (avatar != null && !avatar.isEmpty()) {
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

                user.setAvatar("avatar/" + randomFileName);
            }

            user.setCompany(AuthUtil.getCurrentCompany());
            userRepository.save(user);

            response.setData("user", user);
            response.setSuccessful(true);
            response.success("Saved Successfully. Account created");
            return response;
        } catch (Exception e) {
            return response.error(e);
        }
    }

    public ApiResponse update(User user, MultipartFile avatar) {
        ApiResponse response = new ApiResponse();
        try {
            User dbUser = userRepository.findById(
                    user.getId(), AuthUtil.getCurrentCompanyId()
            ).orElse(new User());
            if (dbUser.getId() == null) {
                return response.error("User not found");
            }

            if (avatar != null && !avatar.isEmpty()) {
                Path directoryPath = Paths.get(avatarDir);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                String originalFilename = avatar.getOriginalFilename();
                String fileExtension = originalFilename != null ?
                        originalFilename.substring(originalFilename.lastIndexOf('.')) : "";
                String randomFileName = UUID.randomUUID().toString() + fileExtension;
                Path filePath = directoryPath.resolve(randomFileName);

                Files.copy(avatar.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                user.setAvatar("avatar/" + randomFileName);
            }

            user.setCompany(AuthUtil.getCurrentCompany());
            userRepository.save(user);
            response.setData("user", user);
            response.setSuccessful(true);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.error(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            User user = userRepository.findById(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(new User());
            if (user.getId() == null) {
                return response.error("User not found");
            }
            response.setData("user", user);
            response.setSuccessful(true);
            response.success("Successfully retrieved user");
            return response;
        } catch (Exception e) {
            return response.error(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            User user = userRepository.findById(
                    id, AuthUtil.getCurrentCompanyId()
            ).orElse(new User());
            if (user.getId() == null) {
                return response.error("User not found");
            }

            userRepository.deleteById(id);
            response.setSuccessful(true);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.error(e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Token token = tokenRepository.findByUsername(username).orElse(null);
        if (token == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return token.getUser();
    }
}