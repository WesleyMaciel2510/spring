package com.example.spring.controller;

import com.example.spring.entity.Usuario;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.user.AppUserCreateRequest;
import com.example.spring.model.requests.user.AppUserUpdateRequest;
import com.example.spring.model.response.DefaultResponse;
import com.example.spring.model.response.IdResponse;
import com.example.spring.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class AppUserController {

    final AppUserService service;

    /*@PostMapping("/search")
    public ResponseEntity<PageResponse<Usuario>> search(@RequestBody AppUserSearchRequest request) {
        Page<Usuario> resultPage = service.search(request);
        return ResponseEntity.ok(PageResponse.fromPage(resultPage));
    }*/

    @PostMapping("/create")
    public ResponseEntity<IdResponse> create(@Valid @RequestBody AppUserCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                IdResponse.builder().id(service.create(request))
                        .message("User created successfully!")
                        .build()
        );
    }

    @PostMapping("/findById")
    public ResponseEntity<Usuario> findById(@RequestBody IdRequest request) {
        return ResponseEntity.ok(service.findById(request));
    }

    @PatchMapping("/update")
    public ResponseEntity<IdResponse> update(@RequestBody AppUserUpdateRequest request) {
        return ResponseEntity.ok(
                IdResponse.builder().id(service.update(request))
                        .message("User updated successfully!")
                        .build()
        );
    }

    @PatchMapping("/delete")
    public ResponseEntity<DefaultResponse> markAsDeleted(@RequestBody IdRequest request) {
        return ResponseEntity.ok(DefaultResponse.builder().message(service.markAsDeleted(request)).build());
    }
}

