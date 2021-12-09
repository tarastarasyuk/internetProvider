package com.internetProvider.dao;

import com.internetProvider.model.User;
import com.internetProvider.security.CryptoUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;

/**
 * USER CRUD - PERFECTO
 * CITY CRUD - PERFECTO
 * SERVICE CRUD - PERFECTO
 * TARIFF CRUD - PERFECTO
 */

public class Main {
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write("h!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
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
