package com.internetProvider.security.servlets;

import com.internetProvider.aservice.CityService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.City;
import com.internetProvider.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ManageUsersServlet", value = "/adminPanel/manageClients")
public class ManageUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService(request);
        List<User> clientList = userService.getAllUsers().stream().filter(user -> user.getRoleId() == 2).collect(Collectors.toList());
        request.setAttribute("clientList", clientList);

        CityService cityService = new CityService(request);
        List<City> cityList = cityService.getAllCities();
        request.setAttribute("cityList", cityList);
        request.getRequestDispatcher("../WEB-INF/jsp/admin/manageClients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
