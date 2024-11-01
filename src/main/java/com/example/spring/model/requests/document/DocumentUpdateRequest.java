package com.example.spring.model.requests.document;

import com.example.spring.model.enums.DocumentTypeEnum;

public record DocumentUpdateRequest(
        String id,
        String name,
        DocumentTypeEnum type,
        Boolean controlledValidity,
        Boolean active) {
}
