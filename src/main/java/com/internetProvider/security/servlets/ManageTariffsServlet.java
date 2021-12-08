package com.internetProvider.security.servlets;

import com.internetProvider.aservice.ServiceService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ManageTariffsServlet", value = "/adminPanel/manageTariffs/*")
public class ManageTariffsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TariffService tariffService = new TariffService(request);
        List<Tariff> tariffList = tariffService.getAllTariffs();
        request.setAttribute("tariffList", tariffList);

        ServiceService serviceService = new ServiceService(request);
        List<Service> serviceList = serviceService.getAllServices();
        request.setAttribute("serviceList", serviceList);
        request.getRequestDispatcher("../WEB-INF/jsp/admin/manageTariffs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getPathInfo();
        if (action != null) {
            switch (action) {
                case "/addNewTariff":
                    addNewTariff(request, session);
                    break;
                case "/editCurrentTariff":
                    editCurrentTariff(request, session);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean editCurrentTariff(HttpServletRequest request, HttpSession session) {
        return true;
    }

    private boolean addNewTariff(HttpServletRequest request, HttpSession session) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Integer price = Integer.valueOf(request.getParameter("price"));
        Integer dayDuration = Integer.valueOf(request.getParameter("dayDuration"));
        int[] serviceIds = Arrays.stream(request.getParameterValues("service")).mapToInt(Integer::parseInt).toArray();
        String[] features = request.getParameterValues("feature");
        return true;
    }
}
