package com.internetProvider.dao;

import com.internetProvider.model.User;

import java.sql.Connection;

public interface UserDAO extends AbstractDAO {
    User findUserByUsernameAndPassword(String username, String password);

    boolean checkUserExistenceByUsername(String username);
}
