package com.example.spring.model.enums;

public enum DocumentTypeEnum {

    CADASTRAL("CA"), LICENCIAMENTO("LI"), CONTRATO("CO");

    private final String code;

    DocumentTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static DocumentTypeEnum fromCode(String code) {
        return switch (code) {
            case "CA" -> CADASTRAL;
            case "LI" -> LICENCIAMENTO;
            case "CO" -> CONTRATO;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case CADASTRAL -> "Cadastral";
            case LICENCIAMENTO -> "Licenciamento";
            case CONTRATO -> "Contrato";
            default -> null;
        };
    }
}
