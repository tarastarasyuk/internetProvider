package com.internetProvider.security.servlets;

import com.internetProvider.security.App;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import static java.util.Objects.nonNull;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (nonNull(session) && nonNull(session.getAttribute(App.Constants.SESSION_USER))) {
            response.sendRedirect(session.getAttribute("pattern").toString());
        } else {
            request.getRequestDispatcher(App.Constants.LOGIN_JSP).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
