package com.internetProvider.dao;

public class QueriesSQL {
    public static final String SELECT_USER_BY_PASSWORD_AND_USERNAME = "SELECT * FROM user WHERE username = ? AND password = ?";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
}
