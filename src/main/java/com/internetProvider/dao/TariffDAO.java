package com.internetProvider.dao;

import com.internetProvider.model.Tariff;

import java.util.List;

public interface TariffDAO extends AbstractDAO<Tariff> {
    List<Tariff> getTariffsByServices(int[] services);
    List<Tariff> getTariffsByServicesSortedBy(int[] services, String field, String order);
    List<Tariff> getTariffsSortedBy(String field, String order);
    boolean checkTariffExistenceByName(String name);
}
