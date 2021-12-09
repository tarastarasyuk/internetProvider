package com.internetProvider.model;

public class Service {
    private int id;
    private String name;
    private String description;
    private String logoLink;

    public Service() {
    }

    public Service(int id, String name, String description, String logoLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logoLink = logoLink;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
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

    public class Builder {
        private final Service service;

        public Builder() {
            this.service = new Service();
        }

        public Builder withId(int id) {
            service.id = id;
            return this;
        }

        public Builder withName(String name) {
            service.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            service.description = description;
            return this;
        }

        public Builder withLogoLink(String logoLink) {
            service.logoLink = logoLink;
            return this;
        }

        public Service buildService() {
            return this.service;
        }
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
