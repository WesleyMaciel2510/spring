package com.example.spring.model.converter;

import com.example.spring.model.enums.DocumentTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DocumentTypeEnumConverter implements AttributeConverter<DocumentTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(DocumentTypeEnum attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getCode();
    }

    @Override
    public DocumentTypeEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return DocumentTypeEnum.fromCode(dbData);
    }
}