package com.internetProvider.dao;

import com.internetProvider.model.Role;
import com.internetProvider.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    private final Connection connection;


    public UserDAOImpl(Connection connection) {
        this.connection = connection;
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
            e.printStackTrace();
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
        user.setRoleId(resultSet.getInt(k++));
        user.setRole(Role.getRole(user.getRoleId()));
        user.setCityId(resultSet.getInt(k));
        return user;
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
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public boolean create(Object entity) {
        return false;
    }

    @Override
    public Object read(int entityId) {
        return null;
    }

    @Override
    public boolean update(Object entity, Object newEntity) {
        return false;
    }

    @Override
    public boolean delete(Object entity) {
        return false;
    }


}
