package com.example.spring.model.requests.user;

import com.example.spring.model.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;

public record AppUserCreateRequest(
        @NotNull(message = "Username is required.")
        String username,
        @NotNull(message = "Password is required.")
        String password,
        @NotNull(message = "Role is required.")
        RoleEnum role
) {}
