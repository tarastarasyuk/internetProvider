package com.internetProvider.security.servlets;

import com.internetProvider.aservice.ServiceService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "TariffServlet", value = "/tariffs")
public class TariffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceService serviceService = new ServiceService(request);
        List<Service> serviceList = serviceService.getAllServices();
        request.setAttribute("serviceList", serviceList);
        TariffService tariffService = new TariffService(request);


        List<Tariff> tariffList;



        if (request.getQueryString() != null) {
            String service = request.getParameter("service");
            String sortBy = request.getParameter("sortBy");
            if (service != null && sortBy != null) {
                String[] values = sortBy.split("_");
                int [] services = getServicesId(request.getParameterValues("service"));
                tariffList = tariffService.getTariffsByServicesSortedBy(services, values[0], values[1]);
                request.setAttribute("selectedServices", Arrays.toString(services));
                request.setAttribute("selectedSortBy", sortBy);
            } else if (service != null)  {
                int[] services = getServicesId(request.getParameterValues("service"));
                tariffList = tariffService.getTariffsByServices(services);
                request.setAttribute("selectedServices", Arrays.toString(services));
            } else if (sortBy != null) {
                String[] values = sortBy.split("_");
                tariffList = tariffService.getTariffsSortedBy(values[0], values[1]);
                request.setAttribute("selectedSortBy", sortBy);
            } else {
                tariffList = tariffService.getAllTariffs();
            }

        } else {

            tariffList = tariffService.getAllTariffs();
//            // for not to be in permanent order after refresh
//            Collections.shuffle(tariffList);
        }


        if (!tariffList.isEmpty()) {
            request.setAttribute("tariffList", tariffList);
        } else {
            request.setAttribute("noSuchTariffs", "Sorry, there are no such tariffs");
        }


        request.getRequestDispatcher("tariffs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private int[] getServicesId(String[] values) {
        return Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
    }
}
