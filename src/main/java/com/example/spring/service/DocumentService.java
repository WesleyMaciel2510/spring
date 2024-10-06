package com.example.spring.service;

import com.example.spring.entity.Document;
import com.example.spring.model.requests.document.DocumentCreateRequest;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    List<Document> findAll();

    Optional<Document> findById(String id);

    Document save(DocumentCreateRequest documentCreateRequest);

    void deleteById(String id);
}
