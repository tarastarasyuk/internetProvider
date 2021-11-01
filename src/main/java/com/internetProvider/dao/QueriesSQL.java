package com.internetProvider.dao;

public class QueriesSQL {
    private static final int USER_ROLE_ID = 2;
    private static final String userTable = "user";
    private static final String userUsername = "username";
    private static final String userPassword = "password";
    private static final String userStatus = "status";
    private static final String userAccount = "account";
    private static final String userEmail = "email";
    private static final String userCreateTime = "create_time";
    private static final String userTariffId = "tariff_id";
    private static final String userRoleId = "role_id";
    private static final String userCityId = "city_id";
    private static final String userId = "id";

    public static final String SELECT_USER_BY_PASSWORD_AND_USERNAME = "SELECT * FROM " + userTable + " WHERE " + userUsername + " = ? AND " + userPassword + " = ?";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM " + userTable + " WHERE " + userUsername + " = ?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM " + userTable + "";
    public static final String DELETE_USER_BY_ID = "DELETE FROM " + userTable + " WHERE " + userId + " = ?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM " + userTable + " WHERE " + userId + " = ?";
    public static final String UPDATE_USER_BY_ID = "UPDATE " + userTable + " SET " + userUsername + "=?, " + userPassword + "=?, " + userEmail + "=?, " + userCityId + "=? WHERE " + userId + "=?";
    public static final String CREATE_USER = "INSERT INTO " + userTable + " (" + userUsername + ", " + userPassword + ", " + userEmail + ", " + userRoleId + ", " + userCityId + ") VALUES (?, ?, ?, " + USER_ROLE_ID + ", ?)";


    private static final String tariffTable = "tariff";
    private static final String tariffName = "name";
    private static final String tariffDescription = "description";
    private static final String tariffPrice = "price";
    private static final String tariffDaysDuration = "days_duration";
    private static final String tariffId = "id";

    public static final String SELECT_ALL_TARIFFS = "SELECT * FROM " + tariffTable;
    public static final String CREATE_TARIFF = "INSERT INTO " + tariffTable + " (" + tariffName + ", " + tariffDescription + ", " + tariffPrice + ", " + tariffDaysDuration + ") VALUES (?, ?, ?, ?)";
    public static final String SELECT_TARIFF_BY_ID = "SELECT FROM " + tariffTable + " WHERE " + tariffId + " = ?";
    public static final String DELETE_TARIFF_BY_ID = "DELETE FROM " + tariffTable + " WHERE " + tariffId + " = ?";
    public static final String UPDATE_TARIFF_BY_ID = "UPDATE " + tariffTable + " SET " + tariffName + " = ?, " + tariffDescription + " = ?, " + tariffPrice + " = ?,  " + tariffDaysDuration + " = ? WHERE " + tariffId + " = ?";


    private static final String serviceTable = "service";
    private static final String serviceName = "name";
    private static final String serviceDescription = "description";
    private static final String serviceId = "id";

    public static final String SELECT_ALL_SERVICE = "SELECT * FROM " + serviceTable;
    public static final String CREATE_SERVICE = "INSERT INTO " + serviceTable + "(" + serviceName + ", " + serviceDescription + ") VALUES (?, ?)";
    public static final String SELECT_SERVICE_BY_ID = "SELECT FROM " + serviceTable + " WHERE " + serviceId + " = ?";
    public static final String DELETE_SERVICE_BY_ID = "DELETE FROM " + serviceTable + " WHERE " + serviceId + " = ?";
    public static final String UPDATE_SERVICE_BY_ID = "UPDATE " + serviceTable + " SET " + serviceName + " = ?, " + serviceDescription + " = ? WHERE " + serviceId + " = ?";

    private static final String tariffHasServiceTable = "tariff_has_service";
    private static final String tariffHasServiceTariffId = "tariff_id";
    private static final String tariffHasServiceServiceId = "service_id";

    /**
     * wht
     */

    private static final String cityTable = "city";
    private static final String cityName = "city_name";
    private static final String cityId = "id";

    public static final String SELECT_ALL_CITY = "SELECT * FROM " + cityTable;
    public static final String CREATE_CITY = "INSERT INTO " + cityTable + "(" + cityName + ") VALUES ?";
    public static final String SELECT_CITY_BY_ID = "SELECT FROM " + cityTable + " WHERE " + cityId + " = ?";
    public static final String DELETE_CITY_BY_ID = "DELETE FROM " + cityTable + " WHERE " + cityId + " = ?";
    public static final String UPDATE_CITY_BY_ID = "UPDATE " + cityTable + " SET " + cityName + " =? WHERE " + serviceId + " =?";

}
