package com.example.spring.repository;

import com.example.spring.entity.BloodDonor;
import com.example.spring.repository.query.BloodDonorQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BloodDonationRepository extends JpaRepository<BloodDonor, String> {
    @Query(value = BloodDonorQuery.SEARCH_PAGEABLE)
    Page<BloodDonor> search(@Param("like") String like, Pageable pageable);

}
