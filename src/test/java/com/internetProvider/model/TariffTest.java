package com.internetProvider.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TariffTest {
    List<Service> serviceList;


    @BeforeEach
    void init() {
        Service service1 = new Service.Builder()
                .withId(1)
                .withName("First")
                .buildService();
        Service service2 = new Service.Builder()
                .withId(2)
                .withName("Second")
                .buildService();
        this.serviceList = Arrays.asList(service1, service2);
    }

    @Test
    void getListOfServiceId() {
        List<Integer> correctIds = Arrays.asList(1,2);
        Tariff tariff = new Tariff.Builder()
                .withServiceList(serviceList)
                .buildTariff();
        assertEquals(tariff.getListOfServiceId(), correctIds);
    }

    @Test
    void getListOfServiceName() {
        List<String> correctNames = Arrays.asList("First","Second");
        Tariff tariff = new Tariff.Builder()
                .withServiceList(serviceList)
                .buildTariff();
        assertEquals(tariff.getListOfServiceName(), correctNames);
    }

    @Test
    void getFeatures() {
        Tariff tariff = new Tariff.Builder()
                .withFeatures("first;second;third")
                .buildTariff();
        assertEquals(tariff.getFeaturesList().size(), 3);
    }
}