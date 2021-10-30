package com.internetProvider.dao;

import com.internetProvider.model.User;
import com.internetProvider.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class UserDAOImplOld implements UserDAO {

//    final String SELECT_USER_BY_PASSWORD_AND_USERNAME = "SELECT * FROM user WHERE username = ? AND password = ?";
//    final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
//    public UserDAOImplOld() {
//    }
//
//
//    public User findUserByUsernameAndPassword(String username, String password, Connection connection) {
//        User user = null;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PASSWORD_AND_USERNAME)) {
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                user = fillUserWithExistingData(resultSet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    private User fillUserWithExistingData(ResultSet resultSet) throws SQLException {
////        User user = new User();
////        int k = 1;
////        user.setId(resultSet.getInt(k++));
////        user.setUsername(resultSet.getString(k++));
////        user.setPassword(resultSet.getString(k++));
////        user.setStatus(User.Status.valueOf(resultSet.getString(k++)));
////        user.setAccount(resultSet.getBigDecimal(k++));
////        user.setEmail(resultSet.getString(k++));
////        user.setDateOfLastChanges(resultSet.getDate(k++));
////        user.setTariffId(resultSet.getInt(k++));
////        user.setRoleId(resultSet.getInt(k));
////        // зробити слой юзер дао з ROLE і далі через valueOf ставити ЄНАМ
////        user.setRole(User.Role.getRole(user.getRoleId()));
////        return user;
//    }
//
//
//    public boolean checkUserExistenceByUsername(String username, Connection connection) {
//        boolean result = false;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                result = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//
//
//
//
//
////    //remove later to constants
////    final String SELECT_USER_BY_PASS_AND_UNAME = "SELECT * FROM user WHERE username = ? and password = ?";
////
////
////    @Override
////    public User findUserByUsernameAndPassword(String username, String password) {
////        User user = null;
////        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection();
////             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PASS_AND_UNAME)) {
////            preparedStatement.setString(1, username);
////            preparedStatement.setString(2, password);
////            ResultSet resultSet = preparedStatement.executeQuery();
////            if (resultSet.next()) {
////                user = createUser(resultSet);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return user;
////    }
////
////    private User createUser(ResultSet resultSet) throws SQLException {
////        User user = new Client();
////        user.setId(resultSet.getInt(1));
////        return user;
////    }
//
////    @Override
////    public User findUserByUsernameAndPassword(String username, String password) {
////        User user = null;
////        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection();
////             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PASS_AND_UNAME)) {
////            preparedStatement.setString(1,user.getUsername());
////            preparedStatement.setString(2,user.getPassword());
////            ResultSet resultSet = preparedStatement.executeQuery();
////            if (resultSet.next()) {
////                user = createUser(resultSet);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return new Client();
////    }
////
////    public boolean userExists(User user) {
////        boolean result = false;
////        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection();
////             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PASS_AND_UNAME)) {
////            preparedStatement.setString(1,user.getUsername());
////            preparedStatement.setString(2,user.getPassword());
////            ResultSet resultSet = preparedStatement.executeQuery();
////            if (resultSet.next()) {
////                user = createUser(resultSet);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return result;
////    }
}
