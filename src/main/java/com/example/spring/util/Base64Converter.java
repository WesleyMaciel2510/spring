package com.example.spring.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public abstract class Base64Converter {

    public static String fromBase64(String string) {
        return new String(Base64.getDecoder().decode(string), StandardCharsets.UTF_8);
    }

}
