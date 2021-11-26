package com.internetProvider.security.servlets;

import com.internetProvider.aservice.ServiceService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TariffServlet", value = "/tariffs")
public class TariffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceService serviceService = new ServiceService(request);
        List<Service> serviceList = serviceService.getAllServices();
        request.setAttribute("serviceList", serviceList);

        TariffService tariffService = new TariffService(request);
        List<Tariff> tariffList = tariffService.getAllTariffs();
        request.setAttribute("tariffList", tariffList);

        List<Tariff> tariffList1 = tariffService.getTariffsByServices( 2);

        request.setAttribute("tariffList", tariffList1);
        request.getRequestDispatcher("tariffs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
