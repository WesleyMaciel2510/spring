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
@Table(name = "LogradouroTipo")
public class ThoroughfareType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LogradouroTipoId")
    @SequenceGenerator(name = "LogradouroTipoId", sequenceName = "LogradouroTipoId", allocationSize = 1)

    @Column(name = "LogradouroTipo_id")
    private Integer id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "SiglaESocial")
    private String eSocialAcronym;

}

