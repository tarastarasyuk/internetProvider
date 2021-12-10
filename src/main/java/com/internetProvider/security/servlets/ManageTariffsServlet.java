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
        TariffService tariffService = TariffService.getInstance(request);
        List<Tariff> tariffList = tariffService.getAllTariffs();
        Collections.reverse(tariffList);
        request.setAttribute("tariffList", tariffList);

        ServiceService serviceService = ServiceService.getInstance(request);
        List<Service> serviceList = serviceService.getAllServices();
        request.setAttribute("serviceList", serviceList);

        request.getRequestDispatcher("../"+ App.Constants.MANAGE_TARIFFS_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TariffService tariffService = TariffService.getInstance(request);
        String action = request.getPathInfo();
        if (action != null) {
            switch (action) {
                case "/addNewTariff":
                    addNewTariff(request, tariffService);
                    break;
                case "/editCurrentTariff":
                    editCurrentTariff(request, tariffService);
                    break;
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

    private boolean editCurrentTariff(HttpServletRequest request, TariffService tariffService) {
        int tariffEditId = Integer.parseInt(request.getParameter("tariffEditId"));
        Tariff tariff = getTariffFromRequest(request);
        return tariffService.updateTariff(tariffEditId, tariff);
    }

    private boolean addNewTariff(HttpServletRequest request, TariffService tariffService) {
        Tariff tariff = getTariffFromRequest(request);
        return tariffService.createNewTariff(tariff);
    }

    private Tariff getTariffFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(request.getParameter("price")));
        int dayDuration = Integer.parseInt(request.getParameter("dayDuration"));
        List<Integer> serviceIds = Arrays.stream(Arrays.stream(request.getParameterValues("service"))
                .mapToInt(Integer::parseInt)
                .toArray())
                .boxed()
                .collect(Collectors.toList());
        String features = String.join(";", request.getParameterValues("feature"));
        return fillTariffWithData(name, description, price, dayDuration, serviceIds, features);
    }

    private Tariff fillTariffWithData(String name, String description, BigDecimal price, int dayDuration, List<Integer> serviceIds, String features) {
        return new Tariff.Builder().withName(name)
                .withDescription(description)
                .withPrice(price)
                .withDayDuration(dayDuration)
                .withListOfServiceId(serviceIds)
                .withFeatures(features)
                .buildTariff();
    }


}
