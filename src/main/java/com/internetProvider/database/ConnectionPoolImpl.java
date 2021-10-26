package com.internetProvider.database;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolImpl {
    private static ConnectionPoolImpl instance;
    private final DataSource ds;

    private ConnectionPoolImpl() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/internet_provider");
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot connect to the connection pool", ex);
        }
    }

    public static synchronized ConnectionPoolImpl getInstance() {
        if (instance == null) {
            instance = new ConnectionPoolImpl();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

