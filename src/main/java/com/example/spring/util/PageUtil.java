package com.example.spring.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT = "id,desc";

    public static Pageable createPageRequest(int pageNumber, int pageSize, String[] sort) {

        if (sort == null || sort.length == 0) {
            return PageRequest.of(pageNumber, pageSize);
        }

        List<Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(Direction.fromString(_sort[1].trim()), _sort[0].trim()));
            }
        } else {
            orders.add(new Order(Direction.fromString(sort[1].trim()), sort[0].trim()));
        }

        return PageRequest.of(pageNumber, pageSize, Sort.by(orders));
    }

}
