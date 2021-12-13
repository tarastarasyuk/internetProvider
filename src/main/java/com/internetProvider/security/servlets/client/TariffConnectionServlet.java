package com.internetProvider.security.servlets.client;

import com.internetProvider.aservice.TariffService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;
import com.internetProvider.security.App;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TariffConnectionServlet", urlPatterns = "/clientPanel/tariffConnection/*")
public class TariffConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        if (userSession.getStatus() != User.Status.BLOCKED) {
            int newTariffId = Integer.parseInt(request.getParameter("newTariffId"));
            TariffService tariffService = TariffService.getInstance(request);
            Tariff newTariff = tariffService.getTariffById(newTariffId);
            User user = (User) session.getAttribute("user");

            if (user.getTariffId() == 0) {
                request.setAttribute("noTariffMessage", "You don't have tariff");
            } else {
                Tariff currentTariff = tariffService.getTariffById(user.getTariffId());
                request.setAttribute("currentTariff", currentTariff);
            }


            ServletContext servletContext = request.getServletContext();
            servletContext.setAttribute("newTariff", newTariff);
            servletContext.setAttribute("user", user);

            request.getRequestDispatcher("../tariffConnection.jsp").forward(request, response);
        } else {
            response.sendRedirect("/"+ App.Constants.TARIFFS_URL);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserService.getInstance(request);

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
        return userService.connectTariff(user, newTariff);
    }
}
