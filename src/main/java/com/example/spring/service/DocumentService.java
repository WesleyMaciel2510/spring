package com.example.spring.service;

import com.example.spring.entity.Document;
import com.example.spring.model.requests.document.DocumentRequest;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    List<Document> findAll();

    Optional<Document> findById(Long id);

    Document save(DocumentRequest documentRequest);

    void deleteById(Long id);
}
