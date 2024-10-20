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
@Table(name = "Cidade")
public class City implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CidadeId")
    @SequenceGenerator(name = "CidadeId", sequenceName = "CidadeId", allocationSize = 1)

    @Column(name = "CidadeId")
    private Integer id;

    @Column(name = "CidadeNome")
    private String name;

    @Column(name = "CidadeMetropolitana")
    private Boolean metropolitanRegion;

    @ManyToOne
    @JoinColumn(name = "EstadoId")
    private State state;

    @Column(name = "CidadeNomeBasico")
    private String basicName;

    @Column(name = "CodigoCorreio")
    private String mailCode;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private City municipality;

    @Column(name = "CidadeIbge")
    private String ibgeCode;

    public City(final Integer id) {
        this.id = id;
    }

    public City(final Integer id, final String name, final String ibgeCode, final Boolean metropolitanRegion) {
        this.ibgeCode = ibgeCode;
        this.id = id;
        this.name = name;
        this.metropolitanRegion = metropolitanRegion;
    }

    @Override
    public City clone() throws CloneNotSupportedException {
        try {
            return (City) super.clone();
        } catch (final CloneNotSupportedException e) {
            return this;
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (id == null) {
            return other.id == null;
        } else {
            return id.equals(other.id);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
