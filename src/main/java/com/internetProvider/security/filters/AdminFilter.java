package com.internetProvider.security.filters;

import com.internetProvider.model.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/adminPanel"})
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
        if (!nonNull(session.getAttribute("user"))) {
            logger.info("No user in session: redirecting to login.jsp");
            res.sendRedirect("login");
        } else {
            User userAdmin = (User) session.getAttribute("user");
            if (userAdmin.getRoleId() == 1) {
                logger.info(userAdmin.getRole() + " logged in");
                chain.doFilter(request, response);
            } else {
                logger.info("Denied access: only ADMIN can get admin panel");
                res.sendRedirect("error.jsp");
            }
        }
    }
}
