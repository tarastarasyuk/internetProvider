package com.internetProvider.dao;

import java.sql.Connection;

public abstract class ConnectionConstructor {
    Connection connection;
    public ConnectionConstructor(Connection connection) {
        this.connection = connection;
    }
}
