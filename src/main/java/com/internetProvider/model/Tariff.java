package com.internetProvider.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tariff {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private int dayDuration;
    private String features;

    public Tariff() {
    }

    public Tariff(int id, String name, String description, BigDecimal price, int dayDuration, String features) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dayDuration = dayDuration;
        this.features = features;
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

