package com.example.spring.service.implementation;

import com.example.spring.entity.Document;
import com.example.spring.model.requests.document.DocumentRequest;
import com.example.spring.repository.DocumentRepository;
import com.example.spring.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }

    public Document save(DocumentRequest documentRequest) {
        Document document = Document.builder()
                .name(documentRequest.getName())
                .type(documentRequest.getType())
                .active(documentRequest.getActive())
                .build();
        return documentRepository.save(document);
    }

    public void deleteById(Long id) {
        documentRepository.deleteById(id);
    }
}
