package com.internetProvider.model;

import java.math.BigDecimal;
import java.util.Date;

public abstract class User {
    private int id;
    private String username;
    private String password;
    private Status status;
    private BigDecimal account;
    private String email;
    private Date dateOfLastChanges;


    public User(String username, String password, Status status, BigDecimal account, String email) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.account = account;
        this.email = email;
    }

    enum Status {
        ACTIVE, BLOCKED
    }
}
