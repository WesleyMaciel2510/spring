package com.example.spring.service;

import com.example.spring.entity.Usuario;
import com.example.spring.model.requests.AuthenticationRequest;
import com.example.spring.model.requests.ResetPasswordRequest;
import com.example.spring.model.response.AuthLoginResponse;
import com.example.spring.model.response.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse authenticate(AuthenticationRequest request, Usuario user);

    AuthLoginResponse login(AuthenticationRequest request);

    void passwordReset(ResetPasswordRequest request);

    AuthLoginResponse generateToken(String username);


}
