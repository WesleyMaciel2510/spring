package com.example.spring.model.requests.user;

import com.example.spring.model.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;

public record AppUserUpdateRequest(
        @NotNull(message = "User ID is required.")
        String id,
        String username,
        String password,
        RoleEnum role
) {}

