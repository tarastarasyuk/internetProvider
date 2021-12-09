package com.internetProvider.dao;

import com.internetProvider.model.User;
import com.internetProvider.security.CryptoUtil;

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
//        LocalDateTime l = LocalDateTime.of(2021, Month.DECEMBER, 5, 2, 4, 40);
//
//        Duration d = Duration.between(l, LocalDateTime.now());
//        System.out.println(d.getSeconds());
//        User.Status s = User.Status.BLOCKED;
//        System.out.println(User.Status.BLOCKED == s);
//
//
//        System.out.println(hash("worktaras", "MD5"));

        System.out.println(CryptoUtil.getEncryptedPassword("petro"));
        System.out.println(CryptoUtil.getEncryptedPassword("stas"));
        System.out.println(CryptoUtil.getEncryptedPassword("vlad"));
        System.out.println(CryptoUtil.getEncryptedPassword("katya"));
        System.out.println(CryptoUtil.getEncryptedPassword("admin"));
        System.out.println(CryptoUtil.getEncryptedPassword("owner"));
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
