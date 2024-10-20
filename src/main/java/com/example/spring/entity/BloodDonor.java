package com.example.spring.entity;

import com.example.spring.model.enums.SexTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "DONOR")
public class BloodDonor extends InfoBase{

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CPF", unique = true, nullable = false)
    private String cpf;

    @Column(name = "RG", nullable = false)
    private String rg;

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEX", nullable = false)
    private SexTypeEnum sex;

    @Column(name = "MOTHER_NAME", nullable = false)
    private String motherName;

    @Column(name = "FATHER_NAME", nullable = false)
    private String fatherName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "POSTAL_CODE", nullable = false)
    private String postalCode;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "ADDRESS_NUMBER", nullable = false)
    private Integer addressNumber;

    @Column(name = "NEIGHBORHOOD", nullable = false)
    private String neighborhood;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "LANDLINE")
    private String landline;

    @Column(name = "MOBILE", nullable = false)
    private String mobile;

    @Column(name = "HEIGHT", nullable = false)
    private Double height; // in meters

    @Column(name = "WEIGHT", nullable = false)
    private Double weight; // in kilograms

    @Column(name = "BLOOD_TYPE", nullable = false)
    private String bloodType;

    @Transient
    private int age; // Computed field for age calculation

    @Transient
    private Double imc; // Computed field for IMC calculation

    public int calculateAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public Double calculateImc() {
        if (height != null && height > 0) {
            return weight / (height * height);
        }
        return null;
    }

    public boolean canDonateBlood() {
        return calculateAge() >= 16 && calculateAge() <= 69 && weight > 50;
    }
}

