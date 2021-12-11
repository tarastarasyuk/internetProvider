package com.internetProvider.security.filters;

import com.internetProvider.model.User;
import com.internetProvider.security.App;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/adminPanel/*"})
public class AdminFilter implements Filter {
    private final static Logger logger = Logger.getLogger(AdminFilter.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();

        // checking user for existing in session
        if (!nonNull(session.getAttribute(App.Constants.SESSION_USER))) {
            logger.info("No user in session: redirecting to login.jsp");
            res.sendRedirect("/"+App.Constants.LOGIN_URL);
        } else {
            User userAdmin = (User) session.getAttribute(App.Constants.SESSION_USER);
            if (userAdmin.getRoleId() == App.Constants.ADMIN_ROLE_ID) {
                // going to AdminPanelServlet
                chain.doFilter(request, response);
            } else {
                // if current user is not 'admin'
                logger.info("Denied access: only ADMIN can get admin panel");
                res.sendRedirect(App.Constants.DONT_HAVE_ACCESS_JSP);
            }
        }

    }
}
