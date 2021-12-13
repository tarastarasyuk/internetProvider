package com.internetProvider.security.servlets.admin;

import com.internetProvider.aservice.CityService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.City;
import com.internetProvider.model.User;
import com.internetProvider.security.App;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ManageUsersServlet", value = "/adminPanel/manageClients/*")
public class ManageClientsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("/clientCreationForm".equals(request.getPathInfo())) {
            response.sendRedirect(App.Constants.CLIENT_CREATION_FORM_URL);
        } else {
            List<User> clientList = getPaginatedClients(request);
            request.setAttribute("clientList", clientList);

            CityService cityService = CityService.getInstance(request);
            List<City> cityList = cityService.getAllCities();
            request.setAttribute("cityList", cityList);
            request.getRequestDispatcher("../"+App.Constants.MANAGE_CLIENTS_JSP).forward(request, response);
        }
    }

    /**
     *
     * @return method return a sample of clients from db depend on request
     * length of sample is stored in variable recordPerPage
     * if request has 'page' parameter then user will get the next sample depend on page number
     */
    private List<User> getPaginatedClients(HttpServletRequest request) {
        UserService userService = UserService.getInstance(request);
        int numOfAllRecords = userService.getNumberOfClients();
        int page = 1;
        int recordPerPage = 6;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int numberOfPages = (int) Math.ceil(numOfAllRecords * 1.0 / recordPerPage);
        List<User> userList = userService.getAllClientsLimitedBy((page - 1)* recordPerPage,recordPerPage);
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        return userList;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action != null) {
            if ("/changeUserStatus".equals(action)) {
                changeUserStatus(request);
            }
        }
        response.sendRedirect("/adminPanel/manageClients");
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
