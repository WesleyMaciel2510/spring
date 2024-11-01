package com.example.spring.repository;

import com.example.spring.entity.Document;
import com.example.spring.model.enums.DocumentTypeEnum;
import com.example.spring.repository.query.DocumentQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    @Query(value = DocumentQuery.SEARCH_PAGEABLE)
    Page<Document> search(@Param("like") String like,
                          @Param("type") DocumentTypeEnum type,
                          @Param("active") Boolean active,
                          Pageable pageable);
}

