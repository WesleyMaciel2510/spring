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
@Table(name = "Bairro")
public class Neighborhood {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BairroId")
    @SequenceGenerator(name = "BairroId", sequenceName = "BairroId", allocationSize = 1)

    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private City cidade;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nome")
    private String nome;

}
