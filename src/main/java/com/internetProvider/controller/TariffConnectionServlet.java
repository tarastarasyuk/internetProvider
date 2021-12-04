package com.internetProvider.controller;

import com.internetProvider.aservice.OwnerService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TariffConnectionServlet", value = "/tariff/tariffConnection/*")
public class TariffConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int newTariffId = Integer.parseInt(request.getParameter("newTariffId"));
        TariffService tariffService = new TariffService(request);
        Tariff newTariff = tariffService.getTariffById(newTariffId);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user.getTariffId() == 0) {
            request.setAttribute("noTariffMessage", "You don't have tariif");
        } else {
            Tariff currentTariff = tariffService.getTariffById(user.getTariffId());
            request.setAttribute("currentTariff", currentTariff);
        }


        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("newTariff", newTariff);
        servletContext.setAttribute("user", user);

        request.getRequestDispatcher("../tariffConnection.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService(request);

        if (payAndConnect(request, userService)) {
            HttpSession session = request.getSession();
            User sessionUser = (User) session.getAttribute("user");
            User updatedUser = userService.getUserByID(sessionUser.getId());
            session.removeAttribute("user");
            session.setAttribute("user", updatedUser);
            response.sendRedirect("../../clientPanel");
        } else
            // TODO:response to error page connected with servers issues
            response.sendRedirect("../../");

    }


    private boolean payAndConnect(HttpServletRequest request, UserService userService) {
        Tariff newTariff = (Tariff) request.getServletContext().getAttribute("newTariff");
        User user = (User) request.getServletContext().getAttribute("user");

        OwnerService ownerService = new OwnerService(request);
        ownerService.getTariffPayment(user, newTariff);

        return userService.setUserTariffById(user.getId(), newTariff.getId());
    }
}
