package com.example.spring.repository;

import com.example.spring.entity.Document;
import com.example.spring.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByUsername(String username);

}
