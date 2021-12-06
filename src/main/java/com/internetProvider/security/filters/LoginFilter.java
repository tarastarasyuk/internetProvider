package com.internetProvider.security.filters;

import com.internetProvider.aservice.UserService;
import com.internetProvider.model.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;


@WebFilter(filterName = "LoginFilter", urlPatterns = {"/clientPanel"})
public class LoginFilter implements Filter {
    private final static Logger logger = Logger.getLogger(LoginFilter.class);

    public void init(FilterConfig config) throws ServletException {
        System.out.println("FILTER WORKS");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //:TODO  to make JavaScript validation for empty input
        UserService userService = new UserService(req);

        HttpSession session = req.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("user"))) {
            User user = (User) session.getAttribute("user");
            if (user.getRoleId() == 1) {
                res.sendRedirect("error.jsp");
            } else
            chain.doFilter(req, res);
        } else
            if (nonNull(username) && nonNull(password)) {

            if (userService.checkUserExistenceByUsername(username)) {
                User existingUser = userService.findUserByUsernameAndPassword(username, password);
                if (existingUser != null) {
                    session.setAttribute("user", existingUser);
                    req.setAttribute("user", existingUser);
                    if (existingUser.getRoleId() == 2) {
                        logger.info("CLIENT WAS LOGGED IN");
                        session.setAttribute("pattern", "clientPanel");
                        res.sendRedirect("clientPanel");
                    } else if (existingUser.getRoleId() == 1) {
                        logger.info("ADMIN WAS LOGGED IN");
                        session.setAttribute("pattern", "adminPanel");
                        res.sendRedirect("adminPanel");
                    }
                } else {
                    logger.info("PASSWORD IS INCORRECT");
                    session.setAttribute("signInError", "password is incorrect...");
                    res.sendRedirect("login");
                }
            } else {
                session.setAttribute("signInError", "no such user...");
                res.sendRedirect("login");
            }

        } else {
                res.sendRedirect("login");
            }
    }
}
