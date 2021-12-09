package com.internetProvider.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private Status status;
    private BigDecimal account;
    private String email;
    private Date creatTime;
    private int tariffId;
    private LocalDateTime tariffBuyDate;
    private int tariffExpiration;
    private int roleId;
    private Role role;
    private Integer cityId;
    private String cityName;

    public User() {
    }

    public int getTariffExpiration() {
        return tariffExpiration;
    }

    public void setTariffExpiration(int tariffExpiration) {
        this.tariffExpiration = tariffExpiration;
    }

    public LocalDateTime getTariffBuyDate() {
        return tariffBuyDate;
    }

    public void setTariffBuyDate(LocalDateTime tariffBuyDate) {
        this.tariffBuyDate = tariffBuyDate;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public enum Status {
        ACTIVE, INACTIVE, BLOCKED
    }


    // Builder pattern
    public class Builder {
        private final User user;

        public Builder() {
            this.user = new User();
        }

        public Builder withId(int id) {
            user.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            user.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder withAccount(BigDecimal account) {
            user.account = account;
            return this;
        }

        public Builder withEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder withCreateTime(Date creatTime) {
            user.creatTime = creatTime;
            return this;
        }

        public Builder withTariffId(int tariffId) {
            user.tariffId = tariffId;
            return this;
        }

        public Builder withTariffBuyDate(LocalDateTime tariffBuyDate) {
            user.tariffBuyDate = tariffBuyDate;
            return this;
        }

        public Builder withTariffExpiration(int tariffExpiration) {
            user.tariffExpiration = tariffExpiration;
            return this;
        }

        public Builder withRoleId(int roleId) {
            user.roleId = roleId;
            return this;
        }

        public Builder withRole(Role role) {
            user.role = role;
            return this;
        }

        public Builder withCityId(int cityId) {
            user.cityId = cityId;
            return this;
        }

        public Builder withCityName(String cityName) {
            user.cityName = cityName;
            return this;
        }

        public User buildUser() {
            return this.user;
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", account=" + account +
                ", email='" + email + '\'' +
                ", creatTime=" + creatTime +
                ", tariffId=" + tariffId +
                ", roleId=" + roleId +
                ", role=" + role +
                ", cityId=" + cityId + "\n" +
                '}';
    }
}
