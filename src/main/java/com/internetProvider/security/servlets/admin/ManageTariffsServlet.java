package com.internetProvider.security.servlets.admin;

import com.internetProvider.aservice.ServiceService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;
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
            response.sendRedirect(App.Constants.TARIFF_CREATION_FORM_URL);
        } else {
            List<Tariff> tariffList = getPaginatedClients(request);
            Collections.reverse(tariffList);
            request.setAttribute("tariffList", tariffList);
            request.getRequestDispatcher("../"+ App.Constants.MANAGE_TARIFFS_JSP).forward(request, response);
        }

    }

    private List<Tariff> getPaginatedClients(HttpServletRequest request) {
        TariffService tariffService = TariffService.getInstance(request);
        int numOfAllRecords = tariffService.getNumberOfTariffs();
        int page = 1;
        int recordPerPage = 6;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int numberOfPages = (int) Math.ceil(numOfAllRecords * 1.0 / recordPerPage);
        List<Tariff> userList = tariffService.getAllTariffsLimitedBy((page - 1)* recordPerPage,recordPerPage);
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        return userList;
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
