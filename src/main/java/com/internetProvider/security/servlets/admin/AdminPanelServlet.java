package com.internetProvider.security.servlets.admin;

import com.internetProvider.aservice.CityService;
import com.internetProvider.model.City;
import com.internetProvider.security.App;

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
                response.sendRedirect(App.Constants.MANAGE_CLIENTS_URL);
                break;
            case "/manageTariffs":
                response.sendRedirect(App.Constants.MANAGE_TARIFFS_URL);
                break;
            default:
                break;
        }
        CityService cityService = CityService.getInstance(request);
        List<City> cityList = cityService.getAllCities();
        request.setAttribute("cityList", cityList);
        request.getRequestDispatcher(App.Constants.ADMIN_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
