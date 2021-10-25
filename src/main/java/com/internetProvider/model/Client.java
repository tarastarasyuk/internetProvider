package com.internetProvider.model;

import java.math.BigDecimal;

public class Client extends User {
    public Client(String username, String password, Status status, BigDecimal account, String email) {
        super(username, password, status, account, email);
    }
}
