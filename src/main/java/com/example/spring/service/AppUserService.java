package com.example.spring.service;

import com.example.spring.entity.Usuario;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.user.AppUserCreateRequest;
import com.example.spring.model.requests.user.AppUserSearchRequest;
import com.example.spring.model.requests.user.AppUserUpdateRequest;
import org.h2.mvstore.Page;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    List<Usuario> findAll();

    Usuario findById(IdRequest request);

    String create(AppUserCreateRequest request);

    String update(AppUserUpdateRequest request);

    String markAsDeleted(IdRequest request);

}

