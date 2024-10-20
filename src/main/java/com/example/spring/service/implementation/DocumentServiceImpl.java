package com.example.spring.service.implementation;

import com.example.spring.entity.Document;
import com.example.spring.model.requests.document.DocumentCreateRequest;
import com.example.spring.repository.DocumentRepository;
import com.example.spring.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentRepository documentRepository;

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Optional<Document> findById(String id) {
        return documentRepository.findById(id);
    }

    public Document save(DocumentCreateRequest documentCreateRequest) {
        Document document = Document.builder()
                .name(documentCreateRequest.getName())
                .type(documentCreateRequest.getType())
                .active(documentCreateRequest.getActive())
                .build();
        return documentRepository.save(document);
    }

    public void deleteById(String id) {
        documentRepository.deleteById(id);
    }
}
