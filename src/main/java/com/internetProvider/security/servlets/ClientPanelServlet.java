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

@WebServlet(name = "ClientPanelServlet", value = "/clientPanel/*")
public class ClientPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityService cityService = new CityService(request);
        List<City> cityList = cityService.getAllCities();
        request.setAttribute("cityList", cityList);
        request.getRequestDispatcher("WEB-INF/jsp/client/client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action != null) {
            switch (action) {
                case "/editProfile":
                    editClientProfile(request);
                    break;
                case "/topUpBalance":
                    break;
                default:
                    break;
            }
        } else {
            doGet(request, response);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserService userService = new UserService(request);
        User updatedUser = userService.getUserByID(user.getId());
        session.removeAttribute("user");
        session.setAttribute("user", updatedUser);
        response.sendRedirect("/clientPanel");

//        HttpSession session = request.getSession();
//        session.removeAttribute("user");
//        request.setAttribute("username", request.getParameter("username"));
//        request.setAttribute("password", request.getParameter("password"));
//        response.sendRedirect("/clientPanel?username="+request.getParameter("username")+"&password="+request.getParameter("password"));
    }

    private void editClientProfile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Integer cityId = Integer.valueOf(request.getParameter("cityId"));
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCityId(cityId);


        UserService userService = new UserService(request);
        userService.updateUser(sessionUser.getId(), user);
    }
}
