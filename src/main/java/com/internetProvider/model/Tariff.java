package com.internetProvider.model;

import java.math.BigDecimal;

public class Tariff {
    private int id;
    private String name;
    private String description;
    private BigDecimal BigDecimal;
    private int dayDuration;

    public Tariff(int id, String name, String description, java.math.BigDecimal bigDecimal, int dayDuration) {
        this.id = id;
        this.name = name;
        this.description = description;
        BigDecimal = bigDecimal;
        this.dayDuration = dayDuration;
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

    public java.math.BigDecimal getBigDecimal() {
        return BigDecimal;
    }

    public void setBigDecimal(java.math.BigDecimal bigDecimal) {
        BigDecimal = bigDecimal;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }
}
