package com.internetProvider.aservice;

import com.internetProvider.dao.DBUtils;
import com.internetProvider.dao.impl.CityDAOImpl;

import com.internetProvider.model.City;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CityService extends AbstractService {

    //    Singleton pattern
    private static CityService instance;
    public static synchronized CityService getInstance(HttpServletRequest request) {
        if (instance == null) {
            instance = new CityService(request);
        }
        return instance;
    }

    private final CityDAOImpl entityDAO;
    private CityService(HttpServletRequest request) {
        super(request);
        entityDAO = new CityDAOImpl(connection);
    }

    public boolean createNewCity(City city) {
        boolean result = entityDAO.create(city);
        DBUtils.commit(connection);
        return result;
    }

    public City getCityById(int id) {
        return entityDAO.read(id);
    }

    public boolean updateCity(int id, City city) {
        boolean result = entityDAO.update(id, city);
        DBUtils.commit(connection);
        return result;
    }

    public boolean deleteCity(int id) {
        boolean result = entityDAO.delete(id);
        DBUtils.commit(connection);
        return result;
    }

    public List<City> getAllCities() {
        return entityDAO.getAll();
    }
}
