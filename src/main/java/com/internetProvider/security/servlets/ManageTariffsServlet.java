package com.internetProvider.security.servlets;

import com.internetProvider.aservice.ServiceService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;
import com.internetProvider.security.App;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ManageTariffsServlet", value = "/adminPanel/manageTariffs/*")
public class ManageTariffsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("/tariffCreationForm".equals(request.getPathInfo())) {
            response.sendRedirect("tariffCreationForm");
        } else {
            TariffService tariffService = TariffService.getInstance(request);
            List<Tariff> tariffList = tariffService.getAllTariffs();
            Collections.reverse(tariffList);
            request.setAttribute("tariffList", tariffList);
            request.getRequestDispatcher("../"+ App.Constants.MANAGE_TARIFFS_JSP).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TariffService tariffService = TariffService.getInstance(request);
        String action = request.getPathInfo();
        if (action != null) {
            switch (action) {
                case "/deleteTariff":
                    deleteTariff(request, tariffService);
                    break;
                default:
                    break;
            }
        }
        response.sendRedirect("/adminPanel/manageTariffs");
    }

    private boolean deleteTariff(HttpServletRequest request, TariffService tariffService) {
        Integer tariffId = Integer.valueOf(request.getParameter("deletedTariff"));
        return tariffService.deleteTariff(tariffId);
    }
}
