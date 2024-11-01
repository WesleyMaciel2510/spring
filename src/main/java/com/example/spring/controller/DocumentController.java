package com.example.spring.controller;

import com.example.spring.entity.Document;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.document.DocumentCreateRequest;
import com.example.spring.model.requests.document.DocumentSaveListRequest;
import com.example.spring.model.requests.document.DocumentSearchRequest;
import com.example.spring.model.requests.document.DocumentUpdateRequest;
import com.example.spring.model.response.DefaultResponse;
import com.example.spring.model.response.IdResponse;
import com.example.spring.model.response.PageResponse;
import com.example.spring.openApi.DocumentControllerOpenAPI;
import com.example.spring.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController implements DocumentControllerOpenAPI {

    final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping("/search")
    public ResponseEntity<PageResponse<Document>> search(@RequestBody DocumentSearchRequest request) {
        return ResponseEntity.ok(PageResponse.fromPage(service.search(request)));
    }

    @PostMapping("/findById")
    public ResponseEntity<Document> findById(@RequestBody IdRequest request) {
        return ResponseEntity.ok(service.findById(request));
    }

    @PostMapping("/create")
    public ResponseEntity<IdResponse> create(@Valid @RequestBody DocumentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                IdResponse.builder().id(service.create(request))
                        .message("Documento criado com sucesso!")
                        .build()
        );
    }

    @PostMapping("/saveList")
    public ResponseEntity<List<String>> saveList(
            @RequestBody DocumentSaveListRequest request) {
        return ResponseEntity.ok(service.saveList(request));
    }

    @PatchMapping("/update")
    public ResponseEntity<IdResponse> update(@RequestBody DocumentUpdateRequest request) {
        return ResponseEntity.ok(
                IdResponse.builder().id(service.update(request))
                        .message("Documento atualizado com sucesso!")
                        .build()
        );
    }

    @PatchMapping("/delete")
    public ResponseEntity<DefaultResponse> markAsDeleted(@RequestBody IdRequest request) {
        return ResponseEntity.ok(DefaultResponse.builder().message(service.markAsDeleted(request)).build());
    }
}

