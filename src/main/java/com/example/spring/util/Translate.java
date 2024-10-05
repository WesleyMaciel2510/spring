package com.example.spring.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public abstract class Translate {

    public static String fromBase64(String string) {
        return new String(Base64.getDecoder().decode(string), StandardCharsets.UTF_8);
    }

    public static String toBase64(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }
}
