package com.example.spring.repository.query;

public class BloodDonorQuery {
    public static final String SEARCH_PAGEABLE = """
                select bd from BloodDonation bd
                where (:like is null or upper(bd.name) like upper(concat('%', :like, '%')))
                and bd.deleted = false
            """;

    public static final String GET_CANDIDATES_BY_STATE = """
                select b.state, count(b) from BloodDonation b
                where b.deleted = false
                group by b.state
            """;
}
