package com.internetProvider.controller;

import com.internetProvider.aservice.TariffService;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TariffConnectionServlet", value = "/tariff/tariffConnection")
public class TariffConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int newTariffId = Integer.parseInt(request.getParameter("newTariffId"));
        System.out.println(newTariffId);
        TariffService tariffService = new TariffService(request);
        Tariff newTariff = tariffService.getTariffById(newTariffId);
        System.out.println(newTariff);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user.getTariffId() == 0) {
            request.setAttribute("noTariffMessage", "You don't have tariif");
        } else {
            Tariff currentTariff = tariffService.getTariffById(user.getTariffId());
            request.setAttribute("currentTariff", currentTariff);
        }




        request.setAttribute("newTariff", newTariff);
        request.setAttribute("user", user);
        request.getRequestDispatcher("../tariffConnection.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("../tariffConnection.jsp").forward(request,response);
    }
}
