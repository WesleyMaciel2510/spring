package com.example.spring.controller;

import com.example.spring.entity.BloodDonation;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorCreateRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorSearchRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorUpdateRequest;
import com.example.spring.model.response.DefaultResponse;
import com.example.spring.model.response.IdResponse;
import com.example.spring.model.response.PageResponse;
import com.example.spring.model.response.ProcessedDataResponse;
import com.example.spring.openApi.BloodDonationControllerOpenAPI;
import com.example.spring.service.BloodDonationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bloodDonation")
public class BloodDonationController implements BloodDonationControllerOpenAPI {

    private final BloodDonationService service;

    public BloodDonationController(BloodDonationService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<IdResponse> create(@RequestBody BloodDonorCreateRequest request) {
        String id = service.create(request);
        IdResponse response = IdResponse.builder()
                .id(id)
                .message("Candidato criado com sucesso!")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PageResponse<BloodDonation>> search(@RequestBody BloodDonorSearchRequest request) {
        return ResponseEntity.ok(PageResponse.fromPage(service.search(request)));
    }

    @PostMapping("/findById")
    public ResponseEntity<BloodDonation> findById(@RequestBody IdRequest request) {
        return ResponseEntity.ok(service.findById(request));
    }

    @PatchMapping("/update")
    public ResponseEntity<IdResponse> update(@RequestBody BloodDonorUpdateRequest request) {
        String id = service.update(request);
        IdResponse response = IdResponse.builder()
                .id(id)
                .message("Candidato atualizado com sucesso!")
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/delete")
    public ResponseEntity<DefaultResponse> markAsDeleted(@RequestBody IdRequest request) {
        DefaultResponse response = DefaultResponse.builder()
                .message(service.markAsDeleted(request))
                .build();
        return ResponseEntity.ok(response);
    }

    // === Requirements ================
    @PostMapping("/processCandidates")
    public ResponseEntity<ProcessedDataResponse> processCandidates(
            @RequestBody List<BloodDonation> candidates,
            @RequestParam String state) {
        ProcessedDataResponse response = service.processCandidates(candidates, state);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/candidatesByState")
    public ResponseEntity<Map<String, Long>> candidatesByState(@RequestParam String state) {
        return ResponseEntity.ok(service.getCandidatesByState(state));
    }

    @GetMapping("/averageImcByAgeRange")
    public ResponseEntity<Map<String, Double>> averageImcByAgeRange() {
        return ResponseEntity.ok(service.getAverageImcByAgeRange());
    }

    @GetMapping("/obesityPercentageByGender")
    public ResponseEntity<Map<String, Double>> obesityPercentageByGender() {
        return ResponseEntity.ok(service.getObesityPercentageByGender());
    }

    @GetMapping("/averageAgeByBloodType")
    public ResponseEntity<Map<String, Double>> averageAgeByBloodType() {
        return ResponseEntity.ok(service.getAverageAgeByBloodType());
    }

    @GetMapping("/possibleDonorsByBloodType")
    public ResponseEntity<Map<String, Long>> possibleDonorsByBloodType() {
        return ResponseEntity.ok(service.getPossibleDonorsByBloodType());
    }
}


