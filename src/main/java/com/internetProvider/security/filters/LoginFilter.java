package com.internetProvider.security.filters;

import com.internetProvider.aservice.UserService;
import com.internetProvider.model.Role;
import com.internetProvider.model.User;

import static java.util.Objects.nonNull;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "LoginFilter", urlPatterns = {"/clientPanel"})
public class LoginFilter implements Filter {
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
        UserService userService = new UserService();

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
                        session.setAttribute("pattern", "clientPanel");
                        res.sendRedirect("clientPanel");
                    } else if (existingUser.getRoleId() == 1) {
                        session.setAttribute("pattern", "adminPanel");
                        res.sendRedirect("adminPanel");
                    }
                } else {
                    session.setAttribute("signInError", "password is incorrect...");
                    res.sendRedirect("login");
                }
            } else {
                session.setAttribute("signInError", "no such user...");
                res.sendRedirect("login");
            }

        }

    }
}
