package com.example.spring.security.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordManager {

    private static final String HASHING_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final String RNG_ALGORITHM = "SHA1PRNG";
    private static final int HASH_BYTE_SIZE = 512;
    private static final int SALT_SIZE = 32;
    private static final int ITERATION_COUNT = 100_000;

    public static String generatePasswordHash(String password) {
        char[] passwordChars = password.toCharArray();
        try {
            byte[] salt = generateSalt();
            byte[] hash = hashPassword(passwordChars, salt);
            return toHex(salt) + ":" + toHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return "";
        }
    }

    public static boolean validatePassword(String password, String storedPasswordHash) {
        String[] parts = storedPasswordHash.split(":");
        if (parts.length != 2) {
            return false;
        }

        byte[] salt = fromHex(parts[0]);
        byte[] storedHash = fromHex(parts[1]);

        try {
            byte[] testHash = hashPassword(password.toCharArray(), salt);
            return compareHashes(storedHash, testHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return false;
        }
    }

    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance(RNG_ALGORITHM);
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] hashPassword(char[] passwordChars, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(passwordChars, salt, ITERATION_COUNT, HASH_BYTE_SIZE);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(HASHING_ALGORITHM);
        return keyFactory.generateSecret(spec).getEncoded();
    }

    private static String toHex(byte[] array) {
        BigInteger bigInt = new BigInteger(1, array);
        String hex = bigInt.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

    private static byte[] fromHex(String hex) {
        int length = hex.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
        }
        return bytes;
    }

    private static boolean compareHashes(byte[] storedHash, byte[] testHash) {
        if (storedHash.length != testHash.length) {
            return false;
        }
        for (int i = 0; i < storedHash.length; i++) {
            if (storedHash[i] != testHash[i]) {
                return false;
            }
        }
        return true;
    }
}
