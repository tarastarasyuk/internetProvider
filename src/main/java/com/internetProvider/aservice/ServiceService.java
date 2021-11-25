package com.internetProvider.aservice;

import com.internetProvider.dao.impl.ServiceDAOImpl;
import com.internetProvider.dao.impl.TariffDAOImpl;
import com.internetProvider.database.ConnectionPoolImpl;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ServiceService extends AbstractService {
    private final ServiceDAOImpl entityDAO;

    public ServiceService(HttpServletRequest request) {
        super(request);
        entityDAO = new ServiceDAOImpl(connection);
    }

    public boolean createNewService(Service service) {
        return entityDAO.create(service);
    }

    public Service getServiceById(int id) {
        return entityDAO.read(id);
    }

    public boolean updateService(int id, Service service) {
        return entityDAO.update(id, service);
    }

    public boolean deleteService(int id) {
        return entityDAO.delete(id);
    }

    public List<Service> getAllServices() {
        return entityDAO.getAll();
    }
}
