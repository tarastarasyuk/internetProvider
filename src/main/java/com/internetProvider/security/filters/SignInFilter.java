package com.internetProvider.security.filters;

import com.internetProvider.dao.UserDAO;
import com.internetProvider.dao.UserDAOImpl;
import com.internetProvider.model.Client;
import com.internetProvider.model.User;
import com.internetProvider.service.UserService;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import static java.util.Objects.nonNull;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "SignInFilter", urlPatterns = "/SingInServlet")
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
            // remove later and do moveToMenu
            req.getRequestDispatcher("/temp_session_suc.jsp").forward(req, res);
//            moveToMenu(res, req, sessionUser.getRole());
        } else {
            try {


                if (userService.checkUserExistenceByUsername(username)) {
                    User existingUser = userService.findUserByUsernameAndPassword(username, password);
                    if (existingUser != null) {
                        session.setAttribute("user", existingUser);
                        // remove later and do moveToMenu
                        req.getRequestDispatcher("/temp_login_suc.jsp").forward(req, res);
//                        moveToMenu(res, req, existingUser.getRole());
                    } else {
                        req.setAttribute("signInError", "The password is incorrect...");
                        req.getRequestDispatcher("/index.jsp").forward(req, res);
                    }
                } else {
                    req.setAttribute("signInError", "There is no such user...");
                    req.getRequestDispatcher("/index.jsp").forward(req, res);
                }




            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        ////////// верхнє норм




//
//
//        if (nonNull(session) && nonNull(session.getAttribute("user"))) {
//            User sessionUser = (User) session.getAttribute("User");
//            moveToMenu(res, req, sessionUser.getRole());
//        } else {
//            User user = userDAO.findUserByUsernameAndPassword(username, password);
//            if (nonNull(user)) {
//                session.setAttribute("user", user);
//                moveToMenu(res, req, user.getRole());
//            } else {
//                req.setAttribute("singInError","Entered data is incorrect... Try again");
//                req.getRequestDispatcher("/index.jsp").forward(req, res);
//            }
//        }
//        /**
//         * 1. По сесії
//         * 2.Користувач існує
//         *   2.1 Користувач існує - редірект на сторінку
//         *   2.2 Користувач існує але не правильний пароль - редірект з повідомленням на логін
//         * 3. Користувача не ічнує - редірект з повідомленням на логін
//         */

    }

    private void moveToMenu(HttpServletResponse res, HttpServletRequest req, User.Role role) throws ServletException, IOException {
        if (role.equals(User.Role.ADMIN)) {
            req.getRequestDispatcher("/admin_menu.jsp").forward(req, res);
        } else if (role.equals(User.Role.CLIENT)) {
            req.getRequestDispatcher("/user_menu.jsp").forward(req, res);
        }
    }
}
