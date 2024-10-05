package com.example.spring.service;

import com.example.spring.entity.RefreshToken;
import com.example.spring.entity.Usuario;
import com.example.spring.model.requests.RefreshTokenRequest;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Usuario user);

    Optional<RefreshToken> findByToken(String token);

    void checkExpiration(RefreshToken token);

    RefreshToken checkRefreshToken(RefreshTokenRequest request);

}