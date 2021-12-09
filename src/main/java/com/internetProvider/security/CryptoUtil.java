package com.internetProvider.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {
    public static String getEncryptedPassword(String password) throws NoSuchAlgorithmException {
        return hash(password, "MD5");
    }

    private static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(input.getBytes());
        return bytesToHex(md.digest()).toUpperCase();
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
