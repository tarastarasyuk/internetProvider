package com.internetProvider.aservice;

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
        return entityDAO.create(tariff);
    }

    public Tariff getTariffById(int id) {
        return entityDAO.read(id);
    }

    public boolean updateTariff(int id, Tariff tariff) {
        return entityDAO.update(id, tariff);
    }

    public boolean deleteTariff(int id) {
        return entityDAO.delete(id);
    }

    public List<Tariff> getAllTariffs() {
        return entityDAO.getAll();
    }

    public List<Tariff> getTariffsByServices(int[] services) {
        return entityDAO.getTariffsByServices(services);
    }

    public List<Tariff> getTariffsSortedBy(String field, String order) { return entityDAO.getTariffsSortedBy(field, order);}

    public List<Tariff> getTariffsByServicesSortedBy(int[] services, String field, String order) {
        return entityDAO.getTariffsByServicesSortedBy(services, field, order);
    }
}
