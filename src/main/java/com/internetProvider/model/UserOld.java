package com.internetProvider.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserOld {
    private int id;
    private String username;
    private String password;
    private Status status;
    private BigDecimal account;
    private String email;
    private Date dateOfLastChanges;
    private Role role;
    private int tariffId;
    private int roleId;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public UserOld() {
    }

    public UserOld(String username, String password, Status status, String email, Role role) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.email = email;
        this.role = role;
    }

    public enum Status {
        ACTIVE("active"), BLOCKED("blocked");

        Status(String status) {
        }
    }

    public enum Role {
        ADMIN, CLIENT;

        public static Role getRole(int i) {
            Role role = null;
            switch (i) {
                case 1:
                    role = Role.ADMIN;
                    break;
                case 2:
                    role = Role.CLIENT;
                    break;
                //todo there is no such role exception...
//                default:
            }
            return role;
        }
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

    public Date getDateOfLastChanges() {
        return dateOfLastChanges;
    }

    public void setDateOfLastChanges(Date dateOfLastChanges) {
        this.dateOfLastChanges = dateOfLastChanges;
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
                ", dateOfLastChanges=" + dateOfLastChanges +
                ", role=" + role +
                ", tariffId=" + tariffId +
                ", roleId=" + roleId +
                '}';
    }
}
