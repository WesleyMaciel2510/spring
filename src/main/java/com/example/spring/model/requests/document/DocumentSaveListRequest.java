package com.example.spring.model.requests.document;

import com.example.spring.entity.Usuario;

import java.util.List;

public record DocumentSaveListRequest(
        Usuario user,
        List<DocumentRequest> documentList
) {
}
