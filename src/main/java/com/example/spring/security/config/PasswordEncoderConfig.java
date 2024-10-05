package com.example.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncoderConfig {
    @Bean(name = "customPasswordEncoder")
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.password.PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return PasswordManager.generatePassword((String) charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return PasswordManager.validatePassword((String) charSequence, s);
            }
        };
    }
}

