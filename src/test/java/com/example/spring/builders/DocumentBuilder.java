package com.example.spring.builders;


import com.example.spring.entity.Document;
import com.example.spring.model.enums.DocumentTypeEnum;

public class DocumentBuilder {

    public static Document document() {
        return Document.builder()
                .name("Documento Teste")
                .type(DocumentTypeEnum.CADASTRAL)
                .active(true)
                .build();
    }
}

