package com.internetProvider.security.servlets;

import com.internetProvider.aservice.CityService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.City;
import com.internetProvider.model.User;
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
        HttpSession session = request.getSession();
        String action = request.getPathInfo();
        System.out.println(action);
        if (action != null) {
            switch (action) {
                case "/changeUserStatus":
                    changeUserStatus(request, session);
                    break;
                case "/addNewClient":
                    addNewClient(request, session);
                    break;
                default:
                    break;
            }
        }
        response.sendRedirect("/adminPanel/manageClients");
    }

    private boolean addNewClient(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = CryptoUtil.getEncryptedPassword(request.getParameter("password"));
        String email = request.getParameter("email");
        Integer cityId = Integer.valueOf(request.getParameter("cityId"));
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCityId(cityId);

        if (nonNull(password)) {
            UserService userService = new UserService(request);
            return userService.createNewUser(user);
        }
        return false;
    }

    private boolean changeUserStatus(HttpServletRequest request, HttpSession session) {
        Integer clientId = Integer.valueOf(request.getParameter("clientId"));
        User.Status clientStatus = User.Status.valueOf(request.getParameter("clientStatus"));
        UserService userService = new UserService(request);
        if (clientStatus == User.Status.BLOCKED) {
            return userService.changeUserStatusByUserId(clientId, User.Status.INACTIVE);
        }
        return userService.changeUserStatusByUserId(clientId, User.Status.BLOCKED);
    }
}
