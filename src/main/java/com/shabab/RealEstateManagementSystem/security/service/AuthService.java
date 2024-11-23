package com.shabab.RealEstateManagementSystem.security.service;

import com.shabab.RealEstateManagementSystem.security.model.Company;
import com.shabab.RealEstateManagementSystem.security.model.Token;
import com.shabab.RealEstateManagementSystem.security.model.User;
import com.shabab.RealEstateManagementSystem.security.repository.TokenRepository;
import com.shabab.RealEstateManagementSystem.security.repository.UserRepository;
import com.shabab.RealEstateManagementSystem.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */


@Service
public class AuthService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;


    public ApiResponse authenticate(Token token) {
        ApiResponse apiResponse = new ApiResponse();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(token.getUsername(), token.getPassword())
            );
            if (authentication != null && authentication.isAuthenticated()) {
                User user = (User) authentication.getPrincipal();
                if (user != null) {
                    Map<String, Object> map = new HashMap<>();
                    String jwt = jwtService.generateJwt(user);
                    map.put("jwt", jwt);

                    apiResponse.setData(map);
                    apiResponse.setData("user", user);
                    apiResponse.success("User authenticated");
                    return apiResponse;
                }
            }
        } catch (Exception e) {
            apiResponse.setMessage("Invalid username or password");
        }
        return apiResponse;
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse register(User user) {
        ApiResponse apiResponse = new ApiResponse();

        try {
            if (user.getUsername() == null || user.getPassword() == null) {
                apiResponse.setMessage("Invalid username or password");
                return apiResponse;
            }
            Token token = new Token();
            token.setUsername(user.getUsername());
            token.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));

            user.setStatus(User.Status.ACTIVE);
            user.setRole(User.Role.ROLE_CUSTOMER);
            user.setToken(null);
            user.setCompany(new Company(1L)); // TODO change to get company dynamically from company URL or something!
            userRepository.save(user);

            token.setUser(user);
            tokenRepository.save(token);
            apiResponse.success("Registration Successful");
        } catch (Exception e) {
            apiResponse.setMessage("Sorry, something went wrong");
        }
        return apiResponse;
    }
}


