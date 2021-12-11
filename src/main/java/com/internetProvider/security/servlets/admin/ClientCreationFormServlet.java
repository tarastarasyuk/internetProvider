package com.internetProvider.security.servlets.admin;

import com.internetProvider.aservice.CityService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.City;
import com.internetProvider.model.User;
import com.internetProvider.security.App;
import com.internetProvider.security.CryptoUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClientCreationFormServlet", urlPatterns = "/adminPanel/manageClients/clientCreationForm/*")
public class ClientCreationFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityService cityService = CityService.getInstance(request);
        List<City> cityList = cityService.getAllCities();
        request.setAttribute("cityList", cityList);
        request.getRequestDispatcher("../../"+ App.Constants.CLIENT_CREATION_FORM_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action != null) {
            if ("/addNewClient".equals(action)) {
                addNewClient(request);
            }
        }
        response.sendRedirect("/adminPanel/manageClients");
    }

    private boolean addNewClient(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = CryptoUtil.getEncryptedPassword(request.getParameter("password"));
        String email = request.getParameter("email");
        int cityId = Integer.parseInt(request.getParameter("cityId"));
        User user = new User.Builder()
                .withUsername(username)
                .withPassword(password)
                .withEmail(email)
                .withCityId(cityId)
                .buildUser();
        UserService userService = UserService.getInstance(request);
        return userService.createNewUser(user);
    }
}
