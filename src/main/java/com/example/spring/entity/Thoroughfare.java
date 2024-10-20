package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Logradouro")
public class Thoroughfare {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LogradouroId")
    @SequenceGenerator(name = "LogradouroId", sequenceName = "LogradouroId", allocationSize = 1)

    @Column(name = "LogradouroId")
    private Integer id;

    @Column(name = "Codigo")
    private String code;

    @Column(name = "Nome")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "bairro_id")
    private Neighborhood neighborhood;

    @ManyToOne
    @JoinColumn(name = "logradouroTipo_id")
    private ThoroughfareType type;

    @Column(name = "Cep")
    private String zipCode;

}