package com.shabab.RealEstateManagementSystem.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;


/**
 * Project: ConstructionAndRealEstateManagement-SpringBoot
 * Author: Shabab
 * Created on: 27/09/2024
 */


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sec_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Cell number is required")
    @Pattern(regexp = "^\\d{11}$", message = "Cell number must be 11 digits")
    @Column(length = 11, nullable = false, unique = true)
    private String cell;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100, unique = true)
    private String email;

    @Size(max = 10, message = "Max 10 Characters")
    @Column(length = 10)
    private String gender;

    @Size(max = 255, message = "Maximum 255 Characters")
    private String address;

    @Column(name = "avatar")
    private String avatar;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    @Column(name = "joining_date")
    private Date joiningDate;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(nullable = false)
    private String status;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @ManyToOne
    private Company company;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Token token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return token.getPassword();
    }

    @Override
    public String getUsername() {
        return token.getUsername();
    }

    public enum Role {
        ROLE_ADMIN,
        ROLE_MANAGER,
        ROLE_EMPLOYEE,
        ROLE_WORKER,
        ROLE_CUSTOMER,
        ROLE_OWNER
    }

}

