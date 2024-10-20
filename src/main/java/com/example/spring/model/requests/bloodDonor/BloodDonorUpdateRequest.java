package com.example.spring.model.requests.bloodDonor;

import java.time.LocalDate;

public record BloodDonorUpdateRequest(
        String id,
        String name,
        String cpf,
        String rg,
        LocalDate birthDate,
        String gender,
        String motherName,
        String fatherName,
        String email,
        String postalCode,
        String address,
        int number,
        String neighborhood,
        String city,
        String state,
        String landline,
        String mobile,
        double height,
        double weight,
        String bloodType
) {
}

