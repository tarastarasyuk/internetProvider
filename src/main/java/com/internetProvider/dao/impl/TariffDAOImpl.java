package com.internetProvider.dao.impl;

import com.internetProvider.dao.ConnectionConstructor;
import com.internetProvider.dao.QueriesSQL;
import com.internetProvider.dao.TariffDAO;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.internetProvider.dao.DBUtils.rollback;

public class TariffDAOImpl extends ConnectionConstructor implements TariffDAO {
    private final static Logger logger = Logger.getLogger(TariffDAOImpl.class);

    public TariffDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Tariff> getAll() {
        return getAllTariffs(QueriesSQL.SELECT_ALL_TARIFFS);
    }

    private Tariff fillTariffWithExistingData(ResultSet resultSet) throws SQLException {
        int idK = 1;
        int k = 1;
        Tariff tariff = new Tariff.Builder().withId(resultSet.getInt(k++))
                .withName(resultSet.getString(k++))
                .withDescription(resultSet.getString(k++))
                .withPrice(resultSet.getBigDecimal(k++))
                .withDayDuration(resultSet.getInt(k++))
                .withFeatures(resultSet.getString(k))
                .withServiceList(getListOfServicesOfCurrentTariff(resultSet.getInt(idK)))
                .buildTariff();
        return tariff;
    }

    private List<Service> getListOfServicesOfCurrentTariff(int id) {
        ServiceDAOImpl serviceDAO = new ServiceDAOImpl(connection);
        List<Service> serviceList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_SERVICE_ID_BY_TARIFF_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                serviceList.add(serviceDAO.read(Integer.parseInt(resultSet.getString(1))));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return serviceList;
    }

    @Override
    public boolean create(Tariff entity) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.CREATE_TARIFF, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBigDecimal(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getDayDuration());
            preparedStatement.setString(5, entity.getFeatures());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println(entity.getListOfServiceId());
                for (Integer serviceId: entity.getListOfServiceId()) {
                    setServiceForCurrentTariff(resultSet.getInt(1), serviceId);
                }
            }

            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }


    private boolean setServiceForCurrentTariff(int tariffId, int serviceId) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SET_SERVICES_FOR_TARIFF)) {
            preparedStatement.setInt(1, tariffId);
            preparedStatement.setInt(2, serviceId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            deleteAllServicesForCurrentTariff(entityId);
            for (Integer serviceId: newEntity.getListOfServiceId()) {
                setServiceForCurrentTariff(entityId, serviceId);
            }
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    private boolean deleteAllServicesForCurrentTariff(int entityId) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.DELETE_FROM_TARIFF_HAS_SERVICE)) {
            preparedStatement.setInt(1, entityId);
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
            logger.error(e.getMessage());
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
        String preparedStatement = QueriesSQL.SELECT_TARIFFS_BY_SERVICES_ORDER_BY.replace("1", field).replace("2", order);
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
            logger.error(e.getMessage());
            rollback(connection);
        }
        return tariffList;
    }


    @Override
    public List<Tariff> getTariffsSortedBy(String field, String order) {
        String statement = QueriesSQL.SELECT_ALL_TARIFFS_ORDER_BY.replace("1", field).replace("2", order);
        return getAllTariffs(statement);
    }

    @Override
    public boolean checkTariffExistenceByName(String name) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_TARIFF_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return result;
    }

    private List<Tariff> getAllTariffs(String statement) {
        List<Tariff> tariffList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tariffList.add(fillTariffWithExistingData(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return tariffList;
    }

    public List<Tariff> getAllTariffsLimitedBy(int offset, int noOfRecords) {
        List<Tariff> tariffList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.SELECT_ALL_TARIFFS_LIMITED_BY)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tariffList.add(fillTariffWithExistingData(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return tariffList;
    }

    public int getNumberOfTariffs() {
        int numberOfTariffs = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueriesSQL.COUNT_ALL_TARIFFS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfTariffs = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollback(connection);
        }
        return numberOfTariffs;
    }
}
