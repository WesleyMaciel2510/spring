package com.example.spring.repository.query;

public class DocumentQuery {

    public static final String SEARCH_PAGEABLE = """
                select d from Document d
                where (:like is null or upper(d.name) like upper(concat('%', :like, '%')))
                and (:type is null or d.type = :type)
                and (:active is null or d.active = :active)
                and d.deleted = false
            """;
}
