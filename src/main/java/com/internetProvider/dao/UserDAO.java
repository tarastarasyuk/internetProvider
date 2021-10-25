package com.internetProvider.dao;

import com.internetProvider.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDAO {

    private final String url = "jdbc:mysql://localhost:3306/internet_provider";
    private final String user = "root";
    private final String password = "root";
//    private final String USER_VALIDATION_QUERY = "SELECT *"
    public boolean validateUser(User userBean) {
        boolean result = false;

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("sth");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }
}
