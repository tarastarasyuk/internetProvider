package com.internetProvider.dao.impl;

import com.internetProvider.dao.CityDAO;
import com.internetProvider.dao.ConnectionConstructor;
import com.internetProvider.dao.QueriesSQL;
import com.internetProvider.model.City;
import com.internetProvider.model.Service;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.internetProvider.dao.DBUtils.rollback;


public class CityDAOImpl extends ConnectionConstructor implements CityDAO {
    private final static Logger logger = Logger.getLogger(CityDAOImpl.class);

    public CityDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<City> getAll() {
        List<City> cityList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_ALL_CITY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cityList.add(fillCityWithExistingData(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return cityList;
    }

    private City fillCityWithExistingData(ResultSet resultSet) throws SQLException {
        int k = 1;
        City city = new City.Builder().withId(resultSet.getInt(k++))
                .withCityName(resultSet.getString(k))
                .buildCity();
        return city;
    }

    @Override
    public boolean create(City entity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.CREATE_CITY)) {
            preparedStatement.setString(1,entity.getCityName());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    @Override
    public City read(int entityId) {
        City city = new City();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_CITY_BY_ID)) {
            preparedStatement.setInt(1, entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                city = fillCityWithExistingData(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return city;
    }

    @Override
    public boolean update(int entityId, City newEntity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.UPDATE_CITY_BY_ID)) {
            preparedStatement.setString(1, newEntity.getCityName());
            preparedStatement.setInt(2, entityId);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.DELETE_CITY_BY_ID)) {
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
