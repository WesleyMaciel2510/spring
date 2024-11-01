package com.example.spring.repository;

import com.example.spring.entity.BloodDonation;
import com.example.spring.repository.query.BloodDonorQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BloodDonationRepository extends JpaRepository<BloodDonation, String> {
    @Query(value = BloodDonorQuery.SEARCH_PAGEABLE)
    Page<BloodDonation> search(@Param("like") String like, Pageable pageable);

    @Query(BloodDonorQuery.GET_CANDIDATES_BY_STATE)
    List<Object[]> candidatesByState(@Param("state") String state);
}
