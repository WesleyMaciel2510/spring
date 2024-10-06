package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "REFRESHTOKEN")
public class RefreshToken {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "ID", length = 40)
    private String id;

    @OneToOne
    @JoinColumn(name = "IDUSER")
    private Usuario user;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @Column(name = "CREATEDATE", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "EXPIRYDATE", nullable = false)
    private LocalDateTime expiryDate;

}