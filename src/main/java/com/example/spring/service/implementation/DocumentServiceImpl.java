package com.example.spring.service.implementation;

import com.example.spring.entity.Document;
import com.example.spring.entity.Usuario;
import com.example.spring.exception.BadRequestException;
import com.example.spring.exception.NotFoundException;
import com.example.spring.model.enums.RoleEnum;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.document.DocumentCreateRequest;
import com.example.spring.model.requests.document.DocumentSaveListRequest;
import com.example.spring.model.requests.document.DocumentSearchRequest;
import com.example.spring.model.requests.document.DocumentUpdateRequest;
import com.example.spring.repository.DocumentRepository;
import com.example.spring.service.DocumentService;
import com.example.spring.service.JwtService;
import com.example.spring.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    final DocumentRepository repo;
    final JwtService jwtService;

    public DocumentServiceImpl(DocumentRepository repo, JwtService jwtService) {
        this.repo = repo;
        this.jwtService = jwtService;
    }

    @Override
    public Page<Document> search(DocumentSearchRequest request) {
        return repo.search(
                request.like(),
                request.type(),
                request.active(),
                PageUtil.createPageRequest(
                        request.pageNumber() != null ? request.pageNumber() : 0,
                        request.pageSize() != null ? request.pageSize() : Integer.MAX_VALUE,
                        request.sort() != null ? request.sort() : null)
        );
    }

    @Override
    public Document findById(IdRequest request) {
        if (request.id() == null) {
            throw new BadRequestException("ID não informado.");
        }
        return repo.findById(request.id())
                .orElseThrow(() -> new NotFoundException("Documento não encontrado."));
    }

    @Override
    @Transactional
    public String create(DocumentCreateRequest request) {
        return repo.save(
                Document.builder()
                        .name(request.getName())
                        .type(request.getType())
                        .active(request.getActive() != null ? request.getActive() : true)
                        .build()
        ).getId();
    }

    @Override
    @Transactional
    public List<String> saveList(DocumentSaveListRequest request) {
        List<String> saveIds = new ArrayList<>();

        Usuario usuario = request.user();

        request.documentList().forEach(item -> {

            Document newDocument = Document.builder()
                    .name(item.document().getName())
                    .type(item.document().getType())
                    .active(item.document().getActive() != null ? item.document().getActive() : true)
                    .build();

            if (item.document().getId() != null) {
                newDocument.setId(item.document().getId());
            }

            newDocument = repo.save(newDocument);
            saveIds.add(newDocument.getId());
        });

        return saveIds;
    }

    @Override
    @Transactional
    public String update(DocumentUpdateRequest request) {

        Document document = findById(new IdRequest(request.id()));

        if (request.name() != null && !request.name().isEmpty() && !request.name().equals(document.getName())) {
            document.setName(request.name());
        }

        if (request.type() != null && !request.type().equals(document.getType())) {
            document.setType(request.type());
        }

        if (request.active() != null && !request.active().equals(document.getActive())) {
            document.setActive(request.active());
        }

        document.setDeleted(false);

        return repo.save(document).getId();
    }

    @Override
    @Transactional
    public String markAsDeleted(IdRequest request) {
        Document document = findById(new IdRequest(request.id()));

        document.setDeleted(true);

        repo.save(document);
        return "Documento marcado como excluído com sucesso.";
    }
}
