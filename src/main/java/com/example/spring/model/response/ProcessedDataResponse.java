package com.example.spring.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessedDataResponse {
    private Map<String, Long> candidatesByState;
    private Map<String, Double> averageImcByAgeRange;
    private Map<String, Double> obesityPercentageByGender;
    private Map<String, Double> averageAgeByBloodType;
    private Map<String, Long> possibleDonorsByBloodType;

}
