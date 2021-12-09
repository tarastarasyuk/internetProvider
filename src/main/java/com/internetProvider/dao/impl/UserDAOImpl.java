package com.internetProvider.dao.impl;

import com.internetProvider.dao.ConnectionConstructor;
import com.internetProvider.dao.QueriesSQL;
import com.internetProvider.dao.UserDAO;
import com.internetProvider.model.Role;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.internetProvider.dao.DBUtils.rollback;

public class UserDAOImpl extends ConnectionConstructor implements UserDAO {
    private final static Logger logger = Logger.getLogger(UserDAOImpl.class);

    public UserDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_USER_BY_PASSWORD_AND_USERNAME)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = fillUserWithExistingData(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return user;
    }

    private User fillUserWithExistingData(ResultSet resultSet) throws SQLException {
        User user = new User();
        int k = 1;
        user.setId(resultSet.getInt(k++));
        user.setUsername(resultSet.getString(k++));
        user.setPassword(resultSet.getString(k++));
        user.setStatus(User.Status.valueOf(resultSet.getString(k++)));
        user.setAccount(resultSet.getBigDecimal(k++));
        user.setEmail(resultSet.getString(k++));
        user.setCreatTime(resultSet.getDate(k++));
        user.setTariffId(resultSet.getInt(k++));
        Timestamp timestamp = resultSet.getTimestamp(k++);
        user.setTariffBuyDate(timestamp != null ? timestamp.toLocalDateTime() : null);
        user.setRoleId(resultSet.getInt(k++));
        user.setRole(Role.getRole(user.getRoleId()));
        user.setCityId(resultSet.getInt(k));
        user.setCityName(getCityNameByCityId(user.getCityId()));
        return user;
    }

    private String getCityNameByCityId(Integer cityId) {
        String result = new String();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_CITY_NAME_BY_ID)) {
            preparedStatement.setInt(1, cityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    @Override
    public boolean checkUserExistenceByUsername(String username) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    @Override
    public boolean changeUserAccountById(int id, BigDecimal newAccount) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.UPDATE_USER_ACCOUNT_BY_ID)) {
            preparedStatement.setBigDecimal(1, newAccount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    @Override
    public boolean setUserTariffById(int userId, Tariff newTariff) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.UPDATE_USER_TARIFF_ID_BY_ID)) {
            if (newTariff.getId() == 0) {
                preparedStatement.setNull(1, 0);
                preparedStatement.setNull(2, 0);
                changeUserStatusByUserId(userId, User.Status.INACTIVE);
            } else {
                preparedStatement.setInt(1, newTariff.getId());
                preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().plusSeconds(newTariff.getDayDuration())));
                changeUserStatusByUserId(userId, User.Status.ACTIVE);
            }
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    @Override
    public boolean deleteUserTariffById(int userId) {
        return setUserTariffById(userId, new Tariff());
    }

    @Override
    public User getUserOwner() {
        User userOwner = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_USER_OWNER)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userOwner = fillUserWithExistingData(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return userOwner;
    }

    @Override
    public boolean updateUserPassword(int userId, String password) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.UPDATE_PASSWORD)) {
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    @Override
    public boolean changeUserStatusByUserId(int userId, User.Status status) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.UPDATE_USER_STATUS_BY_USER_ID)) {
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }


    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(fillUserWithExistingData(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return userList;
    }

    @Override
    public boolean create(User entity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.CREATE_USER)) {
            // TODO Remove duplicates
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setInt(4, entity.getCityId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    @Override
    public User read(int entityId) {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = fillUserWithExistingData(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return user;
    }

    @Override
    public boolean update(int entityId, User newEntity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.UPDATE_USER_BY_ID)) {
            preparedStatement.setString(1, newEntity.getUsername());
            preparedStatement.setString(2, newEntity.getEmail());
            preparedStatement.setInt(3, newEntity.getCityId());
            preparedStatement.setInt(4, entityId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }



    @Override
    public boolean delete(int entityId) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, entityId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }
}
