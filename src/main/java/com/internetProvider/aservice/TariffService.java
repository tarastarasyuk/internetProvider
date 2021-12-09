package com.internetProvider.aservice;

import com.internetProvider.dao.DBUtils;
import com.internetProvider.dao.impl.TariffDAOImpl;
import com.internetProvider.model.Tariff;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TariffService extends AbstractService {
    private TariffDAOImpl entityDAO;

    public TariffService(HttpServletRequest request) {
        super(request);
        entityDAO = new TariffDAOImpl(connection);
    }

    public boolean createNewTariff(Tariff tariff) {
        boolean result = entityDAO.create(tariff);
        DBUtils.commit(connection);
        return result;
    }

    public Tariff getTariffById(int id) {
        return entityDAO.read(id);
    }

    public boolean updateTariff(int id, Tariff tariff) {
        boolean result = entityDAO.update(id, tariff);
        DBUtils.commit(connection);
        return result;
    }

    public boolean deleteTariff(int id) {
        boolean result = entityDAO.delete(id);
        DBUtils.commit(connection);
        return result;
    }

    public List<Tariff> getAllTariffs() {
        return entityDAO.getAll();
    }

    public List<Tariff> getTariffsByServices(int[] services) {
        return entityDAO.getTariffsByServices(services);
    }

    public List<Tariff> getTariffsSortedBy(String field, String order) {
        return entityDAO.getTariffsSortedBy(field, order);
    }

    public List<Tariff> getTariffsByServicesSortedBy(int[] services, String field, String order) {
        return entityDAO.getTariffsByServicesSortedBy(services, field, order);
    }
}
