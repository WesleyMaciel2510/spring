package com.example.spring.entity;

import com.example.spring.model.converter.DocumentTypeEnumConverter;
import com.example.spring.model.enums.DocumentTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class Document extends InfoBase {

    @Column(name = "NOME", nullable = false)
    private String name;

    @Convert(converter = DocumentTypeEnumConverter.class)
    @Column(name = "TIPO")
    private DocumentTypeEnum type;

    @Column(name = "ATIVO", nullable = false)
    private Boolean active;
}
