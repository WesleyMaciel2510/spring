package com.example.spring.service;

import com.example.spring.entity.BloodDonation;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorCreateRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorSearchRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorUpdateRequest;
import com.example.spring.model.response.ProcessedDataResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BloodDonationService {

    String create(BloodDonorCreateRequest request);

    Page<BloodDonation> search(BloodDonorSearchRequest request);

    BloodDonation findById(IdRequest request);

    String update(BloodDonorUpdateRequest request);

    String markAsDeleted(IdRequest request);

    Map<String, Long> getCandidatesByState(final String state);

    Map<String, Double> getAverageImcByAgeRange();

    Map<String, Double> getObesityPercentageByGender();

    Map<String, Double> getAverageAgeByBloodType();

    Map<String, Long> getPossibleDonorsByBloodType();

    ProcessedDataResponse processCandidates(List<BloodDonation> candidates, String state);

}
