package com.internetProvider.dao;

import com.internetProvider.model.User;

import java.math.BigDecimal;
import java.sql.Connection;

public interface UserDAO extends AbstractDAO<User> {
    User findUserByUsernameAndPassword(String username, String password);

    boolean checkUserExistenceByUsername(String username);

    boolean changeUserAccountById(int id, BigDecimal newAccount);
}
