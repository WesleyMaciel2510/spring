package com.example.spring.model.enums;

public enum RoleEnum {

    ADMIN("A"),
    USER("U");

    private final String code;

    RoleEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static RoleEnum fromCode(String code) {
        return switch (code) {
            case "A" -> ADMIN;
            case "U" -> USER;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case ADMIN -> "Administrador";
            case USER -> "Usuário";
            default -> null;
        };
    }
}
