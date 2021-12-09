package com.internetProvider.dao.impl;

import com.internetProvider.dao.ConnectionConstructor;
import com.internetProvider.dao.QueriesSQL;
import com.internetProvider.dao.ServiceDAO;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.internetProvider.dao.DBUtils.rollback;


public class ServiceDAOImpl extends ConnectionConstructor implements ServiceDAO {
    private final static Logger logger = Logger.getLogger(ServiceDAOImpl.class);

    public ServiceDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Service> getAll() {
        List<Service> serviceList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_ALL_SERVICE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                serviceList.add(fillServiceWithExistingData(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return serviceList;
    }

    private Service fillServiceWithExistingData(ResultSet resultSet) throws SQLException {
        int k = 1;
        Service service = new Service.Builder().withId(resultSet.getInt(k++))
                .withName(resultSet.getString(k++))
                .withDescription(resultSet.getString(k++))
                .withLogoLink(resultSet.getString(k))
                .buildService();
        return service;
    }

    @Override
    public boolean create(Service entity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.CREATE_SERVICE)) {
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setString(3, entity.getLogoLink());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    @Override
    public Service read(int entityId) {
        Service service = new Service();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_SERVICE_BY_ID)) {
            preparedStatement.setInt(1, entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                service = fillServiceWithExistingData(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return service;
    }

    @Override
    public boolean update(int entityId, Service newEntity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.UPDATE_SERVICE_BY_ID)) {
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setString(2, newEntity.getDescription());
            preparedStatement.setString(3, newEntity.getLogoLink());
            preparedStatement.setInt(4, entityId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    @Override
    public boolean delete(int entityId) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.DELETE_SERVICE_BY_ID)) {
            preparedStatement.setInt(1, entityId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }
}
