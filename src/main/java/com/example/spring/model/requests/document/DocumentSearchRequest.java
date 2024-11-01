package com.example.spring.model.requests.document;

import com.example.spring.model.enums.DocumentTypeEnum;

public record DocumentSearchRequest(
        String like,
        DocumentTypeEnum type,
        Boolean active,
        Integer pageNumber,
        Integer pageSize,
        String[] sort) {
}
