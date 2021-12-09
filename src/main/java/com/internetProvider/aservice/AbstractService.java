package com.internetProvider.aservice;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public abstract class AbstractService {
    protected Connection connection;

    public AbstractService(HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        connection = (Connection) context.getAttribute("dbConnection");
    }

}
