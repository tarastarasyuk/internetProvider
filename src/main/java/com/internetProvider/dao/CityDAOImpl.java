package com.internetProvider.dao;

import com.internetProvider.model.City;
import com.internetProvider.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl extends ConnectionConstructor implements CityDAO {

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
            e.printStackTrace();
        }
        return cityList;
    }

    private City fillCityWithExistingData(ResultSet resultSet) throws SQLException {
        City city = new City();
        int k = 1;
        city.setCityName(resultSet.getString(1));
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return result;
    }
}
