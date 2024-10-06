package com.example.spring.service.implementation;

import com.example.spring.entity.Usuario;
import com.example.spring.exception.BadRequestException;
import com.example.spring.exception.NotFoundException;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.user.AppUserCreateRequest;
import com.example.spring.model.requests.user.AppUserUpdateRequest;
import com.example.spring.repository.AppUserRepository;
import com.example.spring.service.AppUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository repo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> findAll() {
        return repo.findAll();
    }

    @Override
    public Usuario findById(IdRequest request) {
        if (request.id() == null) {
            throw new BadRequestException("Id não informado.");
        }
        return repo.findById(request.id())
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
    }

    @Override
    @Transactional
    public String create(AppUserCreateRequest request) {
        Usuario user = Usuario.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();
        return repo.save(user).getGuid();
    }

    @Override
    @Transactional
    public String update(AppUserUpdateRequest request) {
        Usuario user = repo.findById(request.id())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (request.username() != null && !request.username().equals(user.getUsername())) {
            user.setUsername(request.username());
        }

        if (request.password() != null && !request.password().equals(user.getPassword())) {
            user.setPassword(request.password());
        }

        if (request.role() != null && !request.role().equals(user.getRole())) {
            user.setRole(request.role());
        }

        return repo.save(user).getGuid();
    }

    @Override
    @Transactional
    public String markAsDeleted(IdRequest request) {
        Usuario user = repo.findById(request.id())
                .orElseThrow(() -> new NotFoundException("User not found"));
        repo.save(user);
        return "User marked as deleted successfully!";
    }
}
