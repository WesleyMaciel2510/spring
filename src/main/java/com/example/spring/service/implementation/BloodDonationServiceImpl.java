package com.example.spring.service.implementation;

import com.example.spring.entity.BloodDonation;
import com.example.spring.exception.BadRequestException;
import com.example.spring.exception.NotFoundException;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorCreateRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorSearchRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorUpdateRequest;
import com.example.spring.model.response.ProcessedDataResponse;
import com.example.spring.repository.BloodDonationRepository;
import com.example.spring.service.BloodDonationService;
import com.example.spring.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BloodDonationServiceImpl implements BloodDonationService {

    final BloodDonationRepository repo;

    public BloodDonationServiceImpl(BloodDonationRepository repo) {
        this.repo = repo;
    }

    @Override
    public String create(BloodDonorCreateRequest request) {
        System.out.println("DATA = " + request);

        BloodDonation donor = BloodDonation.builder()
                .name(request.name())
                .cpf(request.cpf())
                .rg(request.rg())
                .birthDate((request.birthDate()))
                .sex(request.sex())
                .motherName(request.motherName())
                .fatherName(request.fatherName())
                .email(request.email())
                .postalCode(request.postalCode())
                .address(request.address())
                .addressNumber(request.addressNumber())
                .neighborhood(request.neighborhood())
                .city(request.city())
                .state(request.state())
                .landline(request.landline())
                .mobile(request.mobile())
                .height(request.height())
                .weight(request.weight())
                .bloodType(request.bloodType())
                .build();
        return repo.save(donor).getId();
    }

    @Override
    public Page<BloodDonation> search(BloodDonorSearchRequest request) {
        return repo.search(
                request.like(),
                PageUtil.createPageRequest(
                        request.pageNumber() != null ? request.pageNumber() : 0,
                        request.pageSize() != null ? request.pageSize() : Integer.MAX_VALUE,
                        request.sort() != null ? request.sort() : null
                )
        );
    }

    @Override
    public BloodDonation findById(IdRequest request) {
        if (request.id() == null) {
            throw new BadRequestException("ID not provided.");
        }
        return repo.findById(request.id())
                .orElseThrow(() -> new NotFoundException("Blood donor not found."));
    }

    @Override
    public String update(BloodDonorUpdateRequest request) {
        BloodDonation donor = findById(new IdRequest(request.id()));

        if (request.name() != null && !request.name().isEmpty() && !request.name().equals(donor.getName())) {
            donor.setName(request.name());
        }

        if (request.email() != null && !request.email().equals(donor.getEmail())) {
            donor.setEmail(request.email());
        }

        donor.setDeleted(false);
        return repo.save(donor).getId();
    }

    @Override
    public String markAsDeleted(IdRequest request) {
        BloodDonation donor = findById(new IdRequest(request.id()));
        donor.setDeleted(true);
        return "Blood donor: '" + donor.getName() + "' marked as deleted successfully!";
    }

    @Override
    public Map<String, Long> getCandidatesByState(String state) {
        List<Object[]> results = repo.candidatesByState(state);
        return results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Long) result[1]
                ));
    }

    @Override
    public Map<String, Double> getAverageImcByAgeRange() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Double> getObesityPercentageByGender() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Double> getAverageAgeByBloodType() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Long> getPossibleDonorsByBloodType() {
        return new HashMap<>();
    }

    @Override
    public ProcessedDataResponse processCandidates(List<BloodDonation> candidates, String state) {
        ProcessedDataResponse response = new ProcessedDataResponse();

        response.setCandidatesByState(getCandidatesByState(state));
        response.setAverageImcByAgeRange(getAverageImcByAgeRange());
        response.setObesityPercentageByGender(getObesityPercentageByGender());
        response.setAverageAgeByBloodType(getAverageAgeByBloodType());
        response.setPossibleDonorsByBloodType(getPossibleDonorsByBloodType());

        return response;
    }
}

