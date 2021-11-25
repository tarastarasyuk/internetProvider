package com.internetProvider.aservice;

import com.internetProvider.dao.impl.TariffDAOImpl;
import com.internetProvider.model.Tariff;

import javax.servlet.ServletRequest;
import java.util.List;

public class TariffService extends AbstractService {
    private TariffDAOImpl entityDAO;

    public TariffService(ServletRequest request) {
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


}
