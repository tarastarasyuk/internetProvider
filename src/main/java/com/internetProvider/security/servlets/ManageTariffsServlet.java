package com.internetProvider.security.servlets;

import com.internetProvider.aservice.ServiceService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;

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
        TariffService tariffService = TariffService.getInstance(request);
        List<Tariff> tariffList = tariffService.getAllTariffs();
        Collections.reverse(tariffList);
        request.setAttribute("tariffList", tariffList);

        ServiceService serviceService = ServiceService.getInstance(request);
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
                case "/deleteTariff":
                    deleteTariff(request, session);
                    break;
                default:
                    break;
            }
        }
        response.sendRedirect("/adminPanel/manageTariffs");
    }

    private boolean deleteTariff(HttpServletRequest request, HttpSession session) {
        Integer tariffId = Integer.valueOf(request.getParameter("deletedTariff"));
        TariffService tariffService = TariffService.getInstance(request);
        return tariffService.deleteTariff(tariffId);
    }

    private boolean editCurrentTariff(HttpServletRequest request, HttpSession session) {
        Integer tariffEditId = Integer.valueOf(request.getParameter("tariffEditId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(request.getParameter("price")));
        int dayDuration = Integer.parseInt(request.getParameter("dayDuration"));
        List<Integer> serviceIds = Arrays.stream(Arrays.stream(request.getParameterValues("service")).mapToInt(Integer::parseInt).toArray()).boxed().collect(Collectors.toList());
        for(String s :  request.getParameterValues("feature")) {
            System.out.println(s);
        }
        String features = String.join(";", request.getParameterValues("feature"));
        Tariff tariff = new Tariff();
        tariff.setName(name);
        tariff.setDescription(description);
        tariff.setPrice(price);
        tariff.setDayDuration(dayDuration);
        tariff.setListOfServiceId(serviceIds);
        tariff.setFeatures(features);
        for (String s:request.getParameterValues("feature"))
            System.out.println(s);
        System.out.println("==");
        System.out.println(features);
        TariffService tariffService = TariffService.getInstance(request);
        tariffService.updateTariff(tariffEditId, tariff);
        return true;
    }

    private boolean addNewTariff(HttpServletRequest request, HttpSession session) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(request.getParameter("price")));
        int dayDuration = Integer.parseInt(request.getParameter("dayDuration"));
        List<Integer> serviceIds = Arrays.stream(Arrays.stream(request.getParameterValues("service")).mapToInt(Integer::parseInt).toArray()).boxed().collect(Collectors.toList());
        String features = String.join(";", request.getParameterValues("feature"));
        Tariff tariff = new Tariff();
        tariff.setName(name);
        tariff.setDescription(description);
        tariff.setPrice(price);
        tariff.setDayDuration(dayDuration);
        tariff.setListOfServiceId(serviceIds);
        tariff.setFeatures(features);
        TariffService tariffService = TariffService.getInstance(request);
        tariffService.createNewTariff(tariff);
        return true;
    }
}
