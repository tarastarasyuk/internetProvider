package com.internetProvider.security.servlets;

import com.internetProvider.aservice.CityService;
import com.internetProvider.model.City;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.nonNull;

@WebServlet(name = "AdminPanelServlet", value = "/adminPanel")
public class AdminPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (nonNull(action))
        switch (action) {
            case "/manageClients":
                response.sendRedirect("manageClients");
                break;
            case "/manageTariffs":
                response.sendRedirect("manageTariffs");
                break;
            default:
                break;
        }
        CityService cityService = new CityService(request);
        List<City> cityList = cityService.getAllCities();
        request.setAttribute("cityList", cityList);
        request.getRequestDispatcher("WEB-INF/jsp/admin/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
