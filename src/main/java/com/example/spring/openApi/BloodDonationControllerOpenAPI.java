package com.example.spring.openApi;

import com.example.spring.entity.BloodDonation;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorCreateRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorSearchRequest;
import com.example.spring.model.requests.bloodDonor.BloodDonorUpdateRequest;
import com.example.spring.model.response.DefaultResponse;
import com.example.spring.model.response.IdResponse;
import com.example.spring.model.response.PageResponse;
import com.example.spring.model.response.ProcessedDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Tag(name = "Blood Donation", description = "Serviços relacionados a doação de sangue")
public interface BloodDonationControllerOpenAPI {

    @Operation(summary = "Cria um novo candidato a doador de sangue")
    ResponseEntity<IdResponse> create(@RequestBody BloodDonorCreateRequest request);

    @Operation(summary = "Busca candidatos com critérios específicos")
    ResponseEntity<PageResponse<BloodDonation>> search(@RequestBody BloodDonorSearchRequest request);

    @Operation(summary = "Busca um candidato pelo seu ID")
    ResponseEntity<BloodDonation> findById(@RequestBody IdRequest request);

    @Operation(summary = "Atualiza informações de um candidato a doador de sangue")
    ResponseEntity<IdResponse> update(@RequestBody BloodDonorUpdateRequest request);

    @Operation(summary = "Marca um candidato como deletado")
    ResponseEntity<DefaultResponse> markAsDeleted(@RequestBody IdRequest request);

    @Operation(summary = "Processa os dados de uma lista de candidatos")
    ResponseEntity<ProcessedDataResponse> processCandidates(
            @RequestBody List<BloodDonation> candidates,
            @RequestParam String state);

    @Operation(summary = "Obtém a quantidade de candidatos por estado")
    ResponseEntity<Map<String, Long>> candidatesByState(@RequestParam String state);

    @Operation(summary = "Calcula o IMC médio por faixa etária")
    ResponseEntity<Map<String, Double>> averageImcByAgeRange();

    @Operation(summary = "Calcula a porcentagem de obesidade por gênero")
    ResponseEntity<Map<String, Double>> obesityPercentageByGender();

    @Operation(summary = "Calcula a média de idade por tipo sanguíneo")
    ResponseEntity<Map<String, Double>> averageAgeByBloodType();

    @Operation(summary = "Obtém a quantidade de doadores possíveis por tipo sanguíneo")
    ResponseEntity<Map<String, Long>> possibleDonorsByBloodType();
}
