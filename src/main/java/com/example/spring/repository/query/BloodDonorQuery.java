package com.example.spring.repository.query;

public class BloodDonorQuery {
    public static final String SEARCH_PAGEABLE = """
                select bd from BloodDonor bd
                where (:like is null or upper(bd.name) like upper(concat('%', :like, '%')))
                and bd.deleted = false
            """;
}
