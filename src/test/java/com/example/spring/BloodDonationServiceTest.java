package com.example.spring;

import com.example.spring.entity.BloodDonation;
import com.example.spring.model.enums.SexTypeEnum;
import com.example.spring.model.requests.bloodDonor.BloodDonorCreateRequest;
import com.example.spring.repository.BloodDonationRepository;
import com.example.spring.service.implementation.BloodDonationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith({MockitoExtension.class})
public class BloodDonationServiceTest {

    @InjectMocks
    private BloodDonationServiceImpl service;

    @Mock
    private BloodDonationRepository repository;

    @Test
    @DisplayName("Deve criar um candidato a doação de sangue.")
    public void shouldCreateBloodDonor() {
        String uuid = UUID.randomUUID().toString();

        BloodDonorCreateRequest request = new BloodDonorCreateRequest(
                "Wesley Maciel",
                "12345678900",
                "MG1234567",
                LocalDate.of(1990, 5, 20),
                SexTypeEnum.MALE,
                "Mae da Silva",
                "Pai da Silva",
                "wesleymaciel@gmail.com",
                "12345-678",
                "Main Street",
                123,
                "Downtown",
                "CityName",
                "StateName",
                "123456789",
                "987654321",
                1.80,
                75.0,
                "O+"
        );

        BloodDonation donor = BloodDonation.builder()
                .name(request.name())
                .cpf(request.cpf())
                .rg(request.rg())
                .birthDate(request.birthDate())
                .sex(request.sex())
                .motherName(request.motherName())
                .fatherName(request.fatherName())
                .email(request.email())
                .postalCode(request.postalCode())
                .address(request.address())
                .addressNumber(request.number())
                .neighborhood(request.neighborhood())
                .city(request.city())
                .state(request.state())
                .landline(request.landline())
                .mobile(request.mobile())
                .height(request.height())
                .weight(request.weight())
                .bloodType(request.bloodType())
                .build();
        donor.setId(uuid);

        Mockito.when(repository.save(Mockito.any(BloodDonation.class))).thenReturn(donor);

        String result = service.create(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(uuid, result);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(BloodDonation.class));
    }
}

