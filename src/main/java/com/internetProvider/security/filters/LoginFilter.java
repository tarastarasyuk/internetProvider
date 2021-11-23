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
public class SignInFilter implements Filter {
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
        UserService
        UserService userService = new UserService();

        HttpSession session = req.getSession();
        System.out.println("!!!!!!!!!!!!!!!!!!");
        System.out.println(req.getContextPath());
        System.out.println(req.getServletPath());
        System.out.println(req.getPathInfo());
        System.out.println(req.getQueryString());
        System.out.println(req.getRequestURI());
        System.out.println("!!!!!!!!!!!!!!!!!!");

        if (nonNull(session) && nonNull(session.getAttribute("user"))) {
            User sessionUser = (User) session.getAttribute("user");
            req.setAttribute("user", sessionUser);
            moveToMenu(res, req, sessionUser.getRole());
            chain.doFilter(req, res);
        } else {
                if (userService.checkUserExistenceByUsername(username)) {
                    User existingUser = userService.findUserByUsernameAndPassword(username, password);
                    if (existingUser != null) {
                        session.setAttribute("user", existingUser);
                        System.out.println(existingUser);
                        req.setAttribute("user", existingUser);
                        moveToMenu(res, req, existingUser.getRole());
                    } else {
                        req.setAttribute("signInError", "The password is incorrect...");
                        req.getRequestDispatcher("/login.jsp").forward(req, res);
                    }
                } else {
                    req.setAttribute("signInError", "There is no such user...");
                    req.getRequestDispatcher("/login.jsp").forward(req, res);
                }
        }
    }

    private void moveToMenu(HttpServletResponse res, HttpServletRequest req, Role role) throws ServletException, IOException {

        if (role.equals(Role.ADMIN)) {
            res.sendRedirect("adminPanel");
        } else if (role.equals(Role.CLIENT)) {
            req.getRequestDispatcher("WEB-INF/jsp/client/client.jsp").forward(req, res);
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}
