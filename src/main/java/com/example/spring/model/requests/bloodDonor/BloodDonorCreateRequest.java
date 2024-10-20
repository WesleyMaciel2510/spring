package com.example.spring.model.requests.bloodDonor;

import com.example.spring.model.enums.SexTypeEnum;

import java.time.LocalDate;

public record BloodDonorCreateRequest(
        String name,
        String cpf,
        String rg,
        LocalDate birthDate,
        SexTypeEnum sex,
        String motherName,
        String fatherName,
        String email,
        String postalCode,
        String address,
        Integer number,
        String neighborhood,
        String city,
        String state,
        String landline,
        String mobile,
        Double height,
        Double weight,
        String bloodType
) {
}


