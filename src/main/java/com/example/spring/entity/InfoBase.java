package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class InfoBase implements Serializable {

    @Id
    @Column(name = "ID", columnDefinition = "VARCHAR(40)")
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    private String id;

    @Column(name = "DELETED", insertable = false)
    private Boolean deleted;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", deleted=" + deleted +
                "}";
    }

}
