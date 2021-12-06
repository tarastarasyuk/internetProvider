package com.internetProvider.dao.impl;

import com.internetProvider.dao.ConnectionConstructor;
import com.internetProvider.dao.QueriesSQL;
import com.internetProvider.dao.TariffDAO;
import com.internetProvider.database.QueriesConstants;
import com.internetProvider.model.Role;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.internetProvider.database.DBUtils.rollback;

public class TariffDAOImpl extends ConnectionConstructor implements TariffDAO {

    public TariffDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Tariff> getAll() {
        return getAllTariffs(QueriesSQL.SELECT_ALL_TARIFFS);
    }

    private Tariff fillTariffWithExistingData(ResultSet resultSet) throws SQLException {
        Tariff tariff = new Tariff();
        int k = 1;
        tariff.setId(resultSet.getInt(k++));
        tariff.setName(resultSet.getString(k++));
        tariff.setDescription(resultSet.getString(k++));
        tariff.setPrice(resultSet.getBigDecimal(k++));
        tariff.setDayDuration(resultSet.getInt(k++));
        tariff.setFeatures(resultSet.getString(k));
        getListOfServiceIdOfCurrentTariff(tariff.getId());
        tariff.setListOfServiceId(getListOfServiceIdOfCurrentTariff(tariff.getId()));
        tariff.setListOfServiceName(getListOfServiceNameOfCurrentTariff(tariff.getId()));
        return tariff;
    }

    private List<String> getListOfServiceNameOfCurrentTariff(int id) {
        List<String> listOfServiceId = new ArrayList<>();
        try( PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_SERVICE_NAME_BY_TARIFF_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listOfServiceId.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        }
        return listOfServiceId;
    }

    private List<Integer> getListOfServiceIdOfCurrentTariff(int id) {
        List<Integer> listOfServiceId = new ArrayList<>();
        try( PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_SERVICE_ID_BY_TARIFF_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listOfServiceId.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        }
        return listOfServiceId;
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
            preparedStatement.setString(5, entity.getFeatures());
            // TODO: create tariffHasService
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
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
            rollback(connection);
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
            preparedStatement.setString(5, newEntity.getFeatures());
            preparedStatement.setInt(6, entityId);
            // TODO: update tariffHasService
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
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
            rollback(connection);
        }
        return result;
    }

    @Override
    public List<Tariff> getTariffsByServices(int[] services) {
        String statement = setArrayInPreparedStatement(services, QueriesSQL.SELECT_TARIFFS_BY_SERVICES);
        return getAllTariffsByServices(services, statement);
    }

    @Override
    public List<Tariff> getTariffsByServicesSortedBy(int[] services, String field, String order) {
        String preparedStatement = QueriesSQL.SELECT_TARIFFS_BY_SERVICES_ORDER_BY.replace("1",field).replace("2", order);
        String statement = setArrayInPreparedStatement(services, preparedStatement);
        return getAllTariffsByServices(services, statement);
    }

    private String setArrayInPreparedStatement(int[] services, String preparedStatement) {
        String servicesId = Arrays.toString(services)
                .replace("[", "")
                .replace("]", "");
        return preparedStatement.replace("$", servicesId);
    }



    private List<Tariff> getAllTariffsByServices(int[] services, String statement) {
        List<Tariff> tariffList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
//           TODO: connection.createArrayOf() doesn't work, fix it
            preparedStatement.setInt(1, services.length);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tariffList.add(fillTariffWithExistingData(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        }
        return tariffList;
    }



    @Override
    public List<Tariff> getTariffsSortedBy(String field, String order) {
        String statement = QueriesSQL.SELECT_ALL_TARIFFS_ORDER_BY.replace("1",field).replace("2", order);
        return getAllTariffs(statement);
    }

    private List<Tariff> getAllTariffs(String statement) {
        List<Tariff> tariffList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tariffList.add(fillTariffWithExistingData(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        }
        return tariffList;
    }
}
