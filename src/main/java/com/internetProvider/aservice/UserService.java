package com.internetProvider.aservice;

import com.internetProvider.dao.impl.UserDAOImpl;
import com.internetProvider.dao.DBUtils;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class UserService extends AbstractService {
    private final static Logger logger = Logger.getLogger(UserService.class);

//    Singleton pattern
    private static UserService instance;
    public static synchronized UserService getInstance(HttpServletRequest request) {
        if (instance == null) {
            instance = new UserService(request);
        }
        return instance;
    }

    private final UserDAOImpl entityDAO;
    private UserService(HttpServletRequest request) {
        super(request);
        entityDAO = new UserDAOImpl(connection);
    }

    public boolean checkUserExistenceByUsername(String username) {
        return entityDAO.checkUserExistenceByUsername(username);
    }

    public boolean checkUserExistenceByEmail(String email) {
        if (email.isEmpty()) return false;
        return entityDAO.checkUserExistenceByEmail(email);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        User user = entityDAO.findUserByUsernameAndPassword(username, password);
        DBUtils.commit(connection);
        return user;
    }

    public boolean createNewUser(User user) {
        boolean result = entityDAO.create(user);
        DBUtils.commit(connection);
        logger.info("New USER was added to database");
        return result;
    }

    public User getUserByID(int id) {
        return entityDAO.read(id);
    }

    public boolean updateUser(int id, User newUser) {
        boolean result = entityDAO.update(id, newUser);
        DBUtils.commit(connection);
        return result;
    }

    public boolean deleteUser(int id) {
        boolean result = entityDAO.delete(id);
        DBUtils.commit(connection);
        return result;
    }

    public List<User> getAllUsers() {
        return entityDAO.getAll();
    }

    public boolean changeUserAccountById(int id, BigDecimal account) {
        boolean result = entityDAO.changeUserAccountById(id, account);
        DBUtils.commit(connection);
        return result;
    }

    public boolean setUserTariffById(int userId, Tariff newTariff) {
        boolean result = entityDAO.setUserTariffById(userId, newTariff);
        DBUtils.commit(connection);
        return result;
    }

    public boolean deleteUserTariffById(int userId) {
        boolean result = entityDAO.deleteUserTariffById(userId);
        logger.info("CLIENT deleted his tariff");
        DBUtils.commit(connection);
        return result;
    }

    public User getUserOwner() {
        return entityDAO.getUserOwner();
    }

    public boolean changeUserStatusByUserId(int userId, User.Status status) {
        boolean result = entityDAO.changeUserStatusByUserId(userId, status);
        DBUtils.commit(connection);
        return result;
    }

    public boolean connectTariff(User user, Tariff tariff) {
        boolean resultMinusTariffPrice = false;
        boolean resultPLusTariffPrice = false;
        boolean resultSettingTariff = false;
        BigDecimal userAccount = (user.getAccount()).subtract(tariff.getPrice());

        resultMinusTariffPrice = entityDAO.changeUserAccountById(user.getId(), userAccount);
        User userOwner = getUserOwner();

        BigDecimal userOwnerAccount = (userOwner.getAccount()).add(tariff.getPrice());

        resultPLusTariffPrice = changeUserAccountById(userOwner.getId(), userOwnerAccount);

        resultSettingTariff = entityDAO.setUserTariffById(user.getId(), tariff);
        if (resultMinusTariffPrice && resultPLusTariffPrice && resultSettingTariff) {
            DBUtils.commit(connection);
            logger.info(user.getRole() + " paid the price and connected the tariff");
            return true;
        }
        logger.error(user.getRole() + " could not connect the tariff");
        return false;
    }


    public boolean updateUserPassword(int userId, String password) {
        boolean result = entityDAO.updateUserPassword(userId, password);
        DBUtils.commit(connection);
        return result;
    }
}
