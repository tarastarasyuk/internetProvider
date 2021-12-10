package com.internetProvider.security.listener;

import com.internetProvider.dao.ConnectionPoolImpl;
import com.internetProvider.security.App;
import com.internetProvider.security.filters.AdminFilter;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.sql.Connection;
import java.sql.SQLException;

import static com.internetProvider.dao.DBUtils.close;

/**
 * When the server is started - instance of Connection will be put in
 * ServletContextEvent from which every Servlet can get connection
 */
@WebListener
public class ApplicationContextListener implements ServletContextListener {
    private final static Logger logger = Logger.getLogger(ApplicationContextListener.class);

    public ApplicationContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        try {
            Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            context.setAttribute(App.Constants.SCE_INSTANCE_CONNECTION, connection);
            logger.info("Connection is opened");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = (Connection) sce.getServletContext().getAttribute(App.Constants.SCE_INSTANCE_CONNECTION);
        close(connection);
        logger.info("Connection is closed");
    }

}
