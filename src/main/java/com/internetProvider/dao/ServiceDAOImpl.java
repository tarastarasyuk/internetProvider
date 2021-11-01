package com.internetProvider.dao;

import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl extends  ConnectionConstructor implements ServiceDAO{

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
            e.printStackTrace();
        }
        return serviceList;
    }

    private Service fillServiceWithExistingData(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        int k = 1;
        service.setId(resultSet.getInt(k++));
        service.setName(resultSet.getString(k++));
        service.setDescription(resultSet.getString(k));
        return service;
    }

    @Override
    public boolean create(Service entity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.CREATE_SERVICE)) {
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return service;
    }

    @Override
    public boolean update(int entityId, Service newEntity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.UPDATE_SERVICE_BY_ID)) {
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setString(2, newEntity.getDescription());
            preparedStatement.setInt(3, entityId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return result;
    }
}
