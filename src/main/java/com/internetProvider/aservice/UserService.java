package com.internetProvider.aservice;

import com.internetProvider.dao.UserDAOImpl;
import com.internetProvider.database.ConnectionPoolImpl;
import com.internetProvider.model.User;

import java.sql.SQLException;
import java.util.List;

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

    public boolean createNewUser(User user) {
        return userDAO.create(user);
    }

    public User getUserByID(int id) {
        return userDAO.read(id);
    }

    public boolean updateUser(int id, User newUser) {
        return userDAO.update(id, newUser);
    }

    public boolean deleteUser(int id) {
        return userDAO.delete(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }
}
