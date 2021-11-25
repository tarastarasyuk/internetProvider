package com.internetProvider.controller;

import com.internetProvider.aservice.UserAbstractService;
import com.internetProvider.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ClientServlet", urlPatterns = "/addNewClient")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String city = request.getParameter("country");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCityId(2);

        UserAbstractService userService = new UserAbstractService(request);
        userService.createNewUser(user);
        response.sendRedirect("/adminPanel");
    }
}