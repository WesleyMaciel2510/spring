package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "REFRESHTOKEN")
public class RefreshToken {

    @Id
    @Column(name = "ID", length = 40)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @OneToOne
    @JoinColumn(name = "USUARIOID")
    private Usuario user;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @Column(name = "DATACRIACAO", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "DATAEXPIRACAO", nullable = false)
    private LocalDateTime expiryDate;

}