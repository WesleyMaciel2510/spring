package com.example.spring.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponse {
    private String username;
    private String name;
    private String userId;
    private String token;
    private String refreshToken;
}