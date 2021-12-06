package com.internetProvider.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private DBUtils() {}

    public static void commit(Connection con) {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace(); // <-- log
            }
        }
    }

    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace(); // <-- log
            }
        }
    }

    public static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                e.printStackTrace(); // <-- log
            }
        }
    }
}