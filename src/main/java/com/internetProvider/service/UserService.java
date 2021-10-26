package com.internetProvider.service;

import com.internetProvider.dao.UserDAOImpl;
import com.internetProvider.database.ConnectionPoolImpl;
import com.internetProvider.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private ConnectionPoolImpl connectionPool;
    private UserDAOImpl userDAO;

    public UserService() {
        this.connectionPool = ConnectionPoolImpl.getInstance();
        userDAO = new UserDAOImpl();
    }

    public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
        return userDAO.findUserByUsernameAndPassword(username, password, connectionPool.getConnection());
    }

    public boolean checkUserExistenceByUsername(String username) throws SQLException {
        return userDAO.checkUserExistenceByUsername(username, connectionPool.getConnection());
    }


}
