package com.example.spring.model.requests.bloodDonor;

public record BloodDonorSearchRequest(
        String like,
        Boolean active,
        Integer pageNumber,
        Integer pageSize,
        String[] sort) {
}
