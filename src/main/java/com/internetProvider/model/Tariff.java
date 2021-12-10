package com.internetProvider.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class Tariff {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private int dayDuration;
    private String features;
    private List<Service> serviceList;
    private List<Integer> listOfServiceId;

    public Tariff() {
    }

    public Tariff(int id, String name, String description, BigDecimal price, int dayDuration, String features, List<Service> serviceList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dayDuration = dayDuration;
        this.features = features;
        this.serviceList = serviceList;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public List<Integer> getListOfServiceId() {
        if (nonNull(listOfServiceId)) {
            return listOfServiceId;
        }
        return serviceList.stream().map(Service::getId).collect(Collectors.toList());
    }

    public List<String> getListOfServiceName() {
        return serviceList.stream().map(Service::getName).collect(Collectors.toList());
    }

    public void setListOfServiceId(List<Integer> listOfServiceId) {
        this.listOfServiceId = listOfServiceId;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public List<String> getFeaturesList() {
        return Arrays.stream(features.split(";")).map(String::trim).collect(Collectors.toList());
    }

    public static class Builder {
        private final Tariff tariff;

        public Builder() {
            this.tariff = new Tariff();
        }

        private int id;
        private String name;
        private String description;
        private BigDecimal price;
        private int dayDuration;
        private String features;
        private List<Service> serviceList;
        private List<Integer> listOfServiceId;

        public Builder withId(int id) {
            tariff.id = id;
            return this;
        }

        public Builder withName(String name) {
            tariff.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            tariff.description = description;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            tariff.price = price;
            return this;
        }

        public Builder withDayDuration(int dayDuration) {
            tariff.dayDuration = dayDuration;
            return this;
        }

        public Builder withFeatures(String features) {
            tariff.features = features;
            return this;
        }

        public Builder withServiceList(List<Service> serviceList) {
            tariff.serviceList = serviceList;
            return this;
        }

        public Builder withListOfServiceId(List<Integer> listOfServiceId) {
            tariff.listOfServiceId = listOfServiceId;
            return this;
        }

        public Tariff buildTariff() {
            return this.tariff;
        }
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dayDuration=" + dayDuration + "\n" +
                '}';
    }
}

