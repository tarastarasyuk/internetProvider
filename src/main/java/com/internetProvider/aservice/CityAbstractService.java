package com.internetProvider.aservice;

import com.internetProvider.dao.impl.CityDAOImpl;
import com.internetProvider.model.City;

import javax.servlet.ServletRequest;
import java.util.List;

public class CityAbstractService extends AbstractService {
    private CityDAOImpl entityDAO;

    public CityAbstractService(ServletRequest request) {
        super(request);
        entityDAO = new CityDAOImpl(connection);
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
