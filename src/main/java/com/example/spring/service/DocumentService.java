package com.example.spring.service;

import com.example.spring.entity.Document;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.document.DocumentCreateRequest;
import com.example.spring.model.requests.document.DocumentSaveListRequest;
import com.example.spring.model.requests.document.DocumentSearchRequest;
import com.example.spring.model.requests.document.DocumentUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    Page<Document> search(DocumentSearchRequest request);

    Document findById(IdRequest request);

    String create(DocumentCreateRequest request);

    List<String> saveList(DocumentSaveListRequest request);

    String update(DocumentUpdateRequest request);

    String markAsDeleted(IdRequest request);
}
