package com.example.spring.model.enums;

import lombok.Getter;

@Getter
public enum SexTypeEnum {
    FEMALE("F"),
    MALE("M");

    private final String code;

    SexTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SexTypeEnum fromCode(String code) {
        return switch (code) {
            case "F" -> FEMALE;
            case "M" -> MALE;
            default -> null;
        };
    }
}
