package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Estado")
public class State {
    @Id
    @Column(name = "EstadoId")
    private Integer id;

    @Column(name = "EstadoNome")
    private String name;

    @Column(name = "EstadoCodigo")
    private String acronym;

    @Column(name = "EstadoCodigoTiss")
    private String tissCode;

    @OneToMany(mappedBy = "state")
    private List<City> cities;

}
