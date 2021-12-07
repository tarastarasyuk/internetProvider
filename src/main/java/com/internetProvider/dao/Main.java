package com.internetProvider.dao;

import com.internetProvider.model.User;

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
    public static void main(String[] args) throws InterruptedException {
        LocalDateTime l = LocalDateTime.of(2021, Month.DECEMBER, 5, 2, 4, 40);

        Duration d = Duration.between(l, LocalDateTime.now());
        System.out.println(d.getSeconds());
        User.Status s = User.Status.BLOCKED;
        System.out.println(User.Status.BLOCKED == s);
    }
}
