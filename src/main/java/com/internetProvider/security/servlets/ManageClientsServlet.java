package com.internetProvider.security.servlets;

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
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@WebServlet(name = "ManageUsersServlet", value = "/adminPanel/manageClients/*")
public class ManageClientsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserService.getInstance(request);
        List<User> clientList = userService.getAllUsers().stream().filter(user -> user.getRoleId() == App.Constants.USER_ROLE_ID).collect(Collectors.toList());
        request.setAttribute("clientList", clientList);

        CityService cityService = CityService.getInstance(request);
        List<City> cityList = cityService.getAllCities();
        request.setAttribute("cityList", cityList);
        request.getRequestDispatcher("../"+App.Constants.MANAGE_CLIENT_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action != null) {
            switch (action) {
                case "/changeUserStatus":
                    changeUserStatus(request);
                    break;
                case "/addNewClient":
                    addNewClient(request);
                    break;
                default:
                    break;
            }
        }
        response.sendRedirect("/adminPanel/manageClients");
    }

    private boolean addNewClient(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = CryptoUtil.getEncryptedPassword(request.getParameter("password"));
        String email = request.getParameter("email");
        int cityId = Integer.parseInt(request.getParameter("cityId"));
        User user = new User.Builder().withCityName(username)
                .withPassword(password)
                .withEmail(email)
                .withCityId(cityId)
                .buildUser();
        UserService userService = UserService.getInstance(request);
        return userService.createNewUser(user);
    }

    /**
     * Clients with statuses ACTIVE or INACTIVE will be blocked and their tariffs will be deleted
     * Clients with status BLOCKED will UNBLOCKED (will get INACTIVE status)
     */
    private boolean changeUserStatus(HttpServletRequest request) {
        boolean result;
        Integer clientId = Integer.valueOf(request.getParameter("clientId"));
        User.Status clientStatus = User.Status.valueOf(request.getParameter("clientStatus"));
        UserService userService = UserService.getInstance(request);

        if (clientStatus == User.Status.BLOCKED) {
            result = userService.changeUserStatusByUserId(clientId, User.Status.INACTIVE);
        } else {
            userService.deleteUserTariffById(clientId);
            result = userService.changeUserStatusByUserId(clientId, User.Status.BLOCKED);
        }
        return result;
    }
}
