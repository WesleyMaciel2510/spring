package com.example.spring.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("rawtypes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PageResponse<T> {

    private List<T> content;
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private int itemsPage;

    public static <T> PageResponse<T> fromPage(Page<T> page) {
        if (Objects.isNull(page)) {
            return null;
        }

        return PageResponse.<T>builder()
                .content(page.getContent())
                .currentPage(page.getPageable().getPageNumber() + 1)
                .itemsPage(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
