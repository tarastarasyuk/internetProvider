package com.internetProvider.security.servlets;

import com.internetProvider.aservice.CityService;
import com.internetProvider.aservice.OwnerService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.City;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@WebServlet(name = "ClientPanelServlet", value = "/clientPanel/*")
public class ClientPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        TariffService tariffService = new TariffService(request);
        Tariff tariff = tariffService.getTariffById(user.getTariffId());
        UserService userService = new UserService(request);
        OwnerService ownerService = new OwnerService(request);
        if (tariff.getId() != 0) {
            user = defineUserStatusAndTariffExpiration(user, tariff, userService, ownerService);
        }

        session.removeAttribute("user");
        session.setAttribute("user", user);



        CityService cityService = new CityService(request);
        List<City> cityList = cityService.getAllCities();



        request.setAttribute("tariff", tariff);
        request.setAttribute("cityList", cityList);
        request.getRequestDispatcher("WEB-INF/jsp/client/client.jsp").forward(request, response);
    }

    private void refreshSessionUser(HttpSession session, HttpServletRequest request) {
        User userSession = (User) session.getAttribute("user");
        UserService userService = new UserService(request);
        User refreshedUser = userService.getUserByID(userSession.getId());
        session.removeAttribute("user");
        session.setAttribute("user", refreshedUser);
    }

    private User defineUserStatusAndTariffExpiration(User user, Tariff tariff, UserService userService, OwnerService ownerService) {
        Duration difference = Duration.between(user.getTariffBuyDate(), LocalDateTime.now());
        int duration = (int) difference.getSeconds();
        if (duration <= tariff.getDayDuration()) {
            user.setTariffExpiration(duration);
        } else {
            // enough money
            if (user.getAccount().compareTo(tariff.getPrice()) >= 0) {
                ownerService.getTariffPayment(user, tariff);
                user.setStatus(User.Status.ACTIVE);
                user.setTariffExpiration(tariff.getDayDuration());
            } else {
                user.setStatus(User.Status.BLOCKED);
                userService.changeUserStatusByUserId(user.getId(), User.Status.BLOCKED);
                user.setTariffExpiration(0);
            }
        }
        return user;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getPathInfo();
        if (action != null) {
            switch (action) {
                case "/editProfile":
                    editClientProfile(request, session);
                    break;
                case "/deleteTariff":
                    deleteTariff(request, session);
                    break;
                default:
                    break;
            }
        } else {
            doGet(request, response);
        }


        User user = (User) session.getAttribute("user");
        UserService userService = new UserService(request);
        User updatedUser = userService.getUserByID(user.getId());
        session.removeAttribute("user");
        session.setAttribute("user", updatedUser);
        response.sendRedirect("/clientPanel");
    }

    private void deleteTariff(HttpServletRequest request, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        UserService userService = new UserService(request);
        userService.deleteUserTariffById(sessionUser.getId());
    }


    private void editClientProfile(HttpServletRequest request, HttpSession session) {
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
