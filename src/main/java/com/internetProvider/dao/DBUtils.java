package com.internetProvider.dao;

import com.internetProvider.dao.impl.CityDAOImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private final static Logger logger = Logger.getLogger(DBUtils.class);

    private DBUtils() {}

    public static void commit(Connection con) {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}