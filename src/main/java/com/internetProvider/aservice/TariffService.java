package com.internetProvider.aservice;

import com.internetProvider.dao.impl.TariffDAOImpl;
import com.internetProvider.database.ConnectionPoolImpl;
import com.internetProvider.model.Tariff;

import java.sql.SQLException;
import java.util.List;

public class TariffService {
    private TariffDAOImpl entityDAO;

    public TariffService() {
        ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();
        try {
            entityDAO = new TariffDAOImpl(connectionPool.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
