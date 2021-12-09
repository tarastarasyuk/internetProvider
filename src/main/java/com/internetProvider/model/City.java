package com.internetProvider.model;

public class City {
    private int id;
    private String cityName;

    public City() {
    }

    public City(int id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public class Builder {
        private final City city;

        public Builder() {
            this.city = new City();
        }

        public Builder withId(int id) {
            city.id = id;
            return this;
        }

        public Builder withCityName(String cityName) {
            city.cityName = cityName;
            return this;
        }

        public City buildCity() {
            return this.city;
        }
    }
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
