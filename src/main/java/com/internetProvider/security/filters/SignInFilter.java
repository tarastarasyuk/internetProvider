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


@WebFilter(filterName = "SignInFilter", urlPatterns = {"/SingInServlet", "/client/*"})
public class SignInFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = new UserService();

        HttpSession session = req.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("user"))) {
            User sessionUser = (User) session.getAttribute("user");
            moveToMenu(res, req, sessionUser.getRole());
        } else {

            if (userService.checkUserExistenceByUsername(username)) {
                User existingUser = userService.findUserByUsernameAndPassword(username, password);
                if (existingUser != null) {
                    session.setAttribute("user", existingUser);
                    System.out.println(existingUser);
                    moveToMenu(res, req, existingUser.getRole());
                } else {
                    req.setAttribute("signInError", "The password is incorrect...");
                    req.getRequestDispatcher("/index.jsp").forward(req, res);
                }
            } else {
                req.setAttribute("signInError", "There is no such user...");
                req.getRequestDispatcher("/index.jsp").forward(req, res);
            }
        }
    }

    private void moveToMenu(HttpServletResponse res, HttpServletRequest req, Role role) throws ServletException, IOException {

        if (role.equals(Role.ADMIN)) {
            res.sendRedirect("/adminPanel");
        } else if (role.equals(Role.CLIENT)) {
            res.sendRedirect("/clientProfile");
        } else {
            res.sendRedirect("index.jsp");
        }
    }
}
