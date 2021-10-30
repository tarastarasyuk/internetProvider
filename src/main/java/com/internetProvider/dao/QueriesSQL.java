package com.internetProvider.dao;

public class QueriesSQL {
    private static int USER_ROLE_ID = 2;
    public static final String SELECT_USER_BY_PASSWORD_AND_USERNAME = "SELECT * FROM user WHERE username = ? AND password = ?";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    public static final String UPDATE_USER_BY_ID = "UPDATE user SET username=?, password=?, email=?, city_id=? WHERE id=?";
    public static final String CREATE_USER = "INSERT INTO user (username, password, email, role_id, city_id) VALUES (?, ?, ?, "+ USER_ROLE_ID +", ?)";
}
