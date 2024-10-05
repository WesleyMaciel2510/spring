package com.example.spring.util;


import com.example.spring.exception.BusinessException;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class FileUtil {

    public static Blob base64ToBlob(final String base64String) {
        final byte[] decodedByte = Base64.getDecoder().decode(base64String);

        try {
            return new SerialBlob(decodedByte);
        } catch (final SQLException ex) {
            throw new BusinessException("Erro na conversão de base64 para blob.");
        }
    }

}
