package com.internetProvider.security.servlets;

import com.internetProvider.model.User;
import com.internetProvider.security.filters.AdminFilter;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        if (nonNull(session)) {
            User user = (User) session.getAttribute("user");
            session.removeAttribute("user");
            session.invalidate();
            logger.info(user.getRole() + " logged out");
        }
        response.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
