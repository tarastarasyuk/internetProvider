package com.internetProvider.aservice;

import com.internetProvider.dao.impl.CityDAOImpl;
import com.internetProvider.dao.impl.ServiceDAOImpl;
import com.internetProvider.database.ConnectionPoolImpl;
import com.internetProvider.model.City;
import com.internetProvider.model.Service;

import java.sql.SQLException;
import java.util.List;

public class CityService {
    private CityDAOImpl entityDAO;

    public CityService() {
        ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();
        try {
            entityDAO = new CityDAOImpl(connectionPool.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createNewCity(City city) {
        return entityDAO.create(city);
    }

    public City getCityById(int id) {
        return entityDAO.read(id);
    }

    public boolean updateCity(int id, City city) {
        return entityDAO.update(id, city);
    }

    public boolean deleteCity(int id) {
        return entityDAO.delete(id);
    }

    public List<City> getAllCities() {
        return entityDAO.getAll();
    }
}
