package com.example.spring.service;

import com.example.spring.entity.BloodDonor;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorCreateRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorSearchRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface BloodDonationService {

    String create(BloodDonorCreateRequest request);

    Page<BloodDonor> search(BloodDonorSearchRequest request);

    BloodDonor findById(IdRequest request);

    String update(BloodDonorUpdateRequest request);

    String markAsDeleted(IdRequest request);

    Map<String, Long> getCandidatesByState();

    Map<String, Double> getAverageImcByAgeRange();

    Map<String, Double> getObesityPercentageByGender();

    Map<String, Double> getAverageAgeByBloodType();

    Map<String, Long> getPossibleDonorsByBloodType();

}
