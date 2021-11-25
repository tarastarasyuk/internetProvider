package com.internetProvider.aservice;

import com.internetProvider.dao.impl.UserDAOImpl;
import com.internetProvider.model.User;

import javax.servlet.ServletRequest;
import java.util.List;

public class UserAbstractService extends Service {
    /**
     * TODO: REMOVE entityDAO and make AbstractService with factory
     */
    private final UserDAOImpl entityDAO;

    public UserAbstractService(ServletRequest request) {
        super(request);
        entityDAO = new UserDAOImpl(connection);
    }

    public boolean checkUserExistenceByUsername(String username) {
        return entityDAO.checkUserExistenceByUsername(username);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return entityDAO.findUserByUsernameAndPassword(username, password);
    }

    public boolean createNewUser(User user) {
        return entityDAO.create(user);
    }

    public User getUserByID(int id) {
        return entityDAO.read(id);
    }

    public boolean updateUser(int id, User newUser) {
        return entityDAO.update(id, newUser);
    }

    public boolean deleteUser(int id) {
        return entityDAO.delete(id);
    }

    public List<User> getAllUsers() {
        return entityDAO.getAll();
    }
}
