package com.example.spring.model.requests.document;

import com.example.spring.model.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentCreateRequest {
    private String name;
    private DocumentTypeEnum type;
    private Boolean active;
}
