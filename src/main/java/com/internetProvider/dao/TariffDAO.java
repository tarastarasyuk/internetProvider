package com.internetProvider.dao;

import com.internetProvider.model.Tariff;

import java.util.List;

public interface TariffDAO extends AbstractDAO<Tariff> {
    List<Tariff> getTariffsByServices(Integer... serviceId);
    List<Tariff> getTariffsSortedByPrice(String order);
    List<Tariff> getTariffsSortedByABC(String order);
}
