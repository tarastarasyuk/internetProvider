package com.internetProvider.security.listener;

import com.internetProvider.database.ConnectionPoolImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class ApplicationContextListener implements ServletContextListener{

    public ApplicationContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        ServletContext context = sce.getServletContext();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_provider", "root", "root");
            context.setAttribute("dbConnection", connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        Connection connection = (Connection) sce.getServletContext().getAttribute("dbConnection");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
