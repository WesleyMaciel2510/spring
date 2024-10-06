package com.example.spring.controller;

import com.example.spring.model.requests.AuthenticationRequest;
import com.example.spring.model.requests.RefreshTokenRequest;
import com.example.spring.model.requests.ResetPasswordRequest;
import com.example.spring.model.response.AuthLoginResponse;
import com.example.spring.service.AuthService;
import com.example.spring.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController  {

    private final AuthService authenticationService;
    private final RefreshTokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthenticationRequest authRequest) {
        AuthLoginResponse loginResponse = authenticationService.login(authRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PutMapping("/passwordReset")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest passwordResetRequest) {
        authenticationService.passwordReset(passwordResetRequest);
        return ResponseEntity.ok("Senha alterada com sucesso.");
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthLoginResponse> refreshToken(@RequestBody RefreshTokenRequest tokenRequest) {
        String username = tokenService.checkRefreshToken(tokenRequest).getUser().getUsername();
        AuthLoginResponse tokenResponse = authenticationService.generateToken(username);
        return ResponseEntity.ok(tokenResponse);
    }

}


