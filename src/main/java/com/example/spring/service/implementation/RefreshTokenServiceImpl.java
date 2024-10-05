package com.example.spring.service.implementation;

import com.example.spring.entity.RefreshToken;
import com.example.spring.entity.Usuario;
import com.example.spring.exception.BadRequestException;
import com.example.spring.exception.NotFoundException;
import com.example.spring.model.requests.RefreshTokenRequest;
import com.example.spring.repository.RefreshTokenRepository;
import com.example.spring.service.RefreshTokenService;
import com.example.spring.util.Chrono;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    final RefreshTokenRepository refreshTokenRepository;

    @Value("${spring.application.security.jwt.refreshExpirationInMs}")
    private long refreshTokenExpiration; //em ms = 2 hrs e 30 minutos

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken createRefreshToken(Usuario user) {

        List<RefreshToken> refreshTokenOld = refreshTokenRepository.findAllByUserGuid(user.getGuid());
        if (!refreshTokenOld.isEmpty()) {
            refreshTokenRepository.deleteAll(refreshTokenOld);
        }

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Chrono.now().plusSeconds(refreshTokenExpiration / 1000))
                .createDate(Chrono.now())
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {

        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public void checkExpiration(RefreshToken token) {

        if (token.getExpiryDate().isBefore(Chrono.now())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Refresh token está expirado. Por favor realize um novo login.");
        }
    }

    @Override
    public RefreshToken checkRefreshToken(RefreshTokenRequest request) {

        if (request.token() == null) {
            throw new BadRequestException("Token não informado.");
        }

        final Optional<RefreshToken> response = findByToken(request.token());

        if (!response.isPresent()) {
            throw new NotFoundException("Refresh token não encontrado. Por favor realize um novo login.");
        }

        final RefreshToken refreshToken = response.get();
        checkExpiration(refreshToken);

        return refreshToken;
    }
}
