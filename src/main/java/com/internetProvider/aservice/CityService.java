package com.internetProvider.aservice;

import com.internetProvider.dao.impl.CityDAOImpl;
import com.internetProvider.database.DBUtils;
import com.internetProvider.model.City;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CityService extends AbstractService {
    private final CityDAOImpl entityDAO;

    public CityService(HttpServletRequest request) {
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
