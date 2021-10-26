package com.internetProvider.dao;

import com.internetProvider.model.User;
import com.internetProvider.shared.dao.AbstractDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface UserDAO extends AbstractDAO {
    User findUserByUsernameAndPassword(String username, String password, Connection connection);

    boolean checkUserExistenceByUsername(String username, Connection connection);

}
