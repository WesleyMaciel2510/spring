package com.example.spring.controller;

import com.example.spring.entity.BloodDonor;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorCreateRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorSearchRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorUpdateRequest;
import com.example.spring.model.response.DefaultResponse;
import com.example.spring.model.response.IdResponse;
import com.example.spring.model.response.PageResponse;
import com.example.spring.service.BloodDonationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bloodDonation")
public class BloodDonationController {

    final BloodDonationService service;

    public BloodDonationController(BloodDonationService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<IdResponse> create(@RequestBody BloodDonorCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                IdResponse.builder()
                        .id(service.create(request))
                        .message("Candidato criado com sucesso!")
                        .build()
        );
    }

    @PostMapping("/search")
    public ResponseEntity<PageResponse<BloodDonor>> search(@RequestBody BloodDonorSearchRequest request) {
        return ResponseEntity.ok(PageResponse.fromPage(service.search(request)));
    }

    @PostMapping("/findById")
    public ResponseEntity<BloodDonor> findById(@RequestBody IdRequest request) {
        return ResponseEntity.ok(service.findById(request));
    }

    @PatchMapping("/update")
    public ResponseEntity<IdResponse> update(@RequestBody BloodDonorUpdateRequest request) {
        return ResponseEntity.ok(
                IdResponse.builder()
                        .id(service.update(request))
                        .message("Candidato atualizado com sucesso!")
                        .build()
        );
    }

    @PatchMapping("/delete")
    public ResponseEntity<DefaultResponse> markAsDeleted(@RequestBody IdRequest request) {
        return ResponseEntity.ok(DefaultResponse.builder().message(service.markAsDeleted(request)).build());
    }

    // === Requirements ================
    @PostMapping("/candidatesByState")
    public ResponseEntity<Map<String, Long>> candidatesByState() {
        return ResponseEntity.ok(service.getCandidatesByState());
    }

    @PostMapping("/averageImcByAgeRange")
    public ResponseEntity<Map<String, Double>> averageImcByAgeRange() {
        return ResponseEntity.ok(service.getAverageImcByAgeRange());
    }

    @PostMapping("/obesityPercentageByGender")
    public ResponseEntity<Map<String, Double>> obesityPercentageByGender() {
        return ResponseEntity.ok(service.getObesityPercentageByGender());
    }

    @PostMapping("/averageAgeByBloodType")
    public ResponseEntity<Map<String, Double>> averageAgeByBloodType() {
        return ResponseEntity.ok(service.getAverageAgeByBloodType());
    }

    @PostMapping("/possibleDonorsByBloodType")
    public ResponseEntity<Map<String, Long>> possibleDonorsByBloodType() {
        return ResponseEntity.ok(service.getPossibleDonorsByBloodType());
    }
}


