package com.internetProvider.dao;

import java.sql.Connection;

public abstract class ConnectionConstructor {
    protected Connection connection;
    public ConnectionConstructor(Connection connection) {
        this.connection = connection;
    }
}
