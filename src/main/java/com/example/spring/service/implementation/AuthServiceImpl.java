package com.example.spring.service.implementation;

import com.example.spring.entity.RefreshToken;
import com.example.spring.entity.Usuario;
import com.example.spring.exception.BadRequestException;
import com.example.spring.exception.BusinessException;
import com.example.spring.exception.NotFoundException;
import com.example.spring.model.requests.AuthenticationRequest;
import com.example.spring.model.requests.ResetPasswordRequest;
import com.example.spring.model.response.AuthenticationResponse;
import com.example.spring.model.response.AuthLoginResponse;
import com.example.spring.repository.AppUserRepository;
import com.example.spring.service.AuthService;
import com.example.spring.service.JwtService;
import com.example.spring.service.RefreshTokenService;
import com.example.spring.util.Base64Converter;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String VALID_PASS_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

    private final PasswordEncoder passwordEncoder;

    private final AppUserRepository appUserRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final RefreshTokenService refreshTokenService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request, Usuario user) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthLoginResponse login(AuthenticationRequest request) {
        if (StringUtils.isBlank(request.getUsername()) || StringUtils.isBlank(request.getPassword())) {
            throw new BadRequestException("Usuário ou senha não informado.");
        }

        Usuario user = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        String jwtToken = authenticate(request, user).getToken();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthLoginResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken.getToken())
                .userId(user.getGuid())
                .name(user.getUsername())
                .username(user.getUsername())
                .build();
    }

    public AuthLoginResponse generateToken(String username) {

        Usuario user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthLoginResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken.getToken())
                .userId(user.getGuid())
                .name(user.getUsername())
                .username(user.getUsername())
                .build();
    }

    @Override
    public void passwordReset(ResetPasswordRequest request) {
        Usuario usuario = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        validatePassword(usuario.getPassword(), request.getStepOne(), request.getStepTwo());

        String newPassword = passwordEncoder.encode(request.getStepOne());

        usuario.setPassword(newPassword);

        appUserRepository.save(usuario);
    }

    private void validatePassword(String passOld, String passOne, String passTwo) {
        if (StringUtils.isBlank(passOne) || StringUtils.isBlank(passTwo)) {
            throw new BadRequestException("Valor não informado para nova senha.");
        }

        passOne = Base64Converter.fromBase64(passOne);
        passTwo = Base64Converter.fromBase64(passTwo);

        if (!passOne.equals(passTwo)) {
            throw new BusinessException(
                    "Senhas divergentes, certifique-se de que ambos os campos sejam preenchidos com a mesma senha.");
        }

        Pattern pattern = Pattern.compile(VALID_PASS_REGEX);

        if (!pattern.matcher(passOne).matches()) {
            throw new BusinessException(
                    "A senha deve conter entre 8 e 20 caracteres, uma letra maiúscula, uma letra minúscula e um número.");
        }

        if (!StringUtils.isBlank(passOld) && passwordEncoder.matches(passOne, passOld)) {
            throw new BusinessException("A senha informada foi usada anteriormente, favor informar uma nova senha.");
        }
    }
}
