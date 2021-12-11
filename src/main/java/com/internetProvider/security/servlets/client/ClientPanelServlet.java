package com.internetProvider.security.servlets.client;

import com.internetProvider.aservice.CityService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.City;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;
import com.internetProvider.security.CryptoUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@WebServlet(name = "ClientPanelServlet", value = "/clientPanel/*")
public class ClientPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        HttpSession session = request.getSession();
        if (nonNull(action))
            switch (action) {
                case "/payment":
                    response.sendRedirect("payment");
                    break;
                case "/editClientForm":
                    response.sendRedirect("editClientForm");
                    break;
                default:
                    break;
        } else {
            refreshSessionUser(request);
            User user = (User) session.getAttribute("user");
            CityService cityService = CityService.getInstance(request);
            List<City> cityList = cityService.getAllCities();
            TariffService tariffService = TariffService.getInstance(request);
            Tariff tariff = tariffService.getTariffById(user.getTariffId());
            request.setAttribute("tariff", tariff);
            request.setAttribute("cityList", cityList);
            request.getRequestDispatcher("WEB-INF/jsp/client/client.jsp").forward(request, response);
        }
    }

    private void refreshSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserService userService = UserService.getInstance(request);
        user = userService.getUserByID(user.getId());
        TariffService tariffService = TariffService.getInstance(request);
        Tariff tariff = tariffService.getTariffById(user.getTariffId());

        if (tariff.getId() != 0) {
            user = defineUserStatusAndTariffExpiration(user, tariff, userService);
        }
        session.removeAttribute("user");
        session.setAttribute("user", user);
    }

    private User defineUserStatusAndTariffExpiration(User user, Tariff tariff, UserService userService) {
        Duration difference = Duration.between(LocalDateTime.now(), user.getTariffBuyDate());
        int duration = (int) difference.getSeconds();
        if (duration > 0) {
            user.setTariffExpiration(duration);
        } else {
            // enough money
            if (user.getAccount().compareTo(tariff.getPrice()) >= 0) {
                userService.connectTariff(user, tariff);
                user.setStatus(User.Status.ACTIVE);
                user.setAccount(user.getAccount().subtract(tariff.getPrice()));
                user.setTariffExpiration(tariff.getDayDuration());
            } else {
                userService.changeUserStatusByUserId(user.getId(), User.Status.BLOCKED);
                user.setStatus(User.Status.BLOCKED);
                user.setTariffExpiration(0);
            }
        }
        return user;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        refreshSessionUser(request);
        HttpSession session = request.getSession();
        String action = request.getPathInfo();
        if (action != null) {
            switch (action) {
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
        UserService userService = UserService.getInstance(request);
        User updatedUser = userService.getUserByID(user.getId());
        session.removeAttribute("user");
        session.setAttribute("user", updatedUser);
        response.sendRedirect("/clientPanel");
    }

    private void deleteTariff(HttpServletRequest request, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        UserService userService = UserService.getInstance(request);
        userService.deleteUserTariffById(sessionUser.getId());
    }

}
