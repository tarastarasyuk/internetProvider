package com.internetProvider.dao.impl;

import com.internetProvider.dao.ConnectionConstructor;
import com.internetProvider.dao.QueriesSQL;
import com.internetProvider.dao.TariffDAO;
import com.internetProvider.model.Role;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TariffDAOImpl extends ConnectionConstructor implements TariffDAO {

    public TariffDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Tariff> getAll() {
        List<Tariff> tariffList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_ALL_TARIFFS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tariffList.add(fillTariffWithExistingData(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tariffList;
    }

    private Tariff fillTariffWithExistingData(ResultSet resultSet) throws SQLException {
        Tariff tariff = new Tariff();
        int k = 1;
        tariff.setId(resultSet.getInt(k++));
        tariff.setName(resultSet.getString(k++));
        tariff.setDescription(resultSet.getString(k++));
        tariff.setPrice(resultSet.getBigDecimal(k++));
        tariff.setDayDuration(resultSet.getInt(k));
        return tariff;
    }

    @Override
    public boolean create(Tariff entity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.CREATE_TARIFF)) {
            // TODO Remove duplicates
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBigDecimal(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getDayDuration());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Tariff read(int entityId) {
        Tariff tariff = new Tariff();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_TARIFF_BY_ID)) {
            preparedStatement.setInt(1, entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tariff = fillTariffWithExistingData(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tariff;
    }

    @Override
    public boolean update(int entityId, Tariff newEntity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.UPDATE_TARIFF_BY_ID)) {
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setString(2, newEntity.getDescription());
            preparedStatement.setBigDecimal(3, newEntity.getPrice());
            preparedStatement.setInt(4, newEntity.getDayDuration());
            preparedStatement.setInt(5, entityId);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.DELETE_TARIFF_BY_ID)) {
            preparedStatement.setInt(1, entityId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
