package com.internetProvider.dao;

import com.internetProvider.database.ConnectionPoolImpl;
import com.internetProvider.model.User;

import java.sql.SQLException;

public class UserService {
    private UserDAOImpl userDAO;

    public UserService() {
        ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();
        try {
            userDAO = new UserDAOImpl(connectionPool.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean checkUserExistenceByUsername(String username) {
        return userDAO.checkUserExistenceByUsername(username);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return userDAO.findUserByUsernameAndPassword(username, password);
    }
}
