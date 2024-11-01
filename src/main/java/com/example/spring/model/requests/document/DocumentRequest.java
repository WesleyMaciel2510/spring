package com.example.spring.model.requests.document;

import com.example.spring.entity.Document;

public record DocumentRequest(
        String documentSupplierId,
        String attachmentDate,
        String expiryDate,
        Document document
) {
}
