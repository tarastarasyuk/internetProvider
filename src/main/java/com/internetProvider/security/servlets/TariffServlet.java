package com.internetProvider.security.servlets;

import com.internetProvider.aservice.ServiceService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TariffServlet", value = "/tariffs")
public class TariffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceService serviceService = new ServiceService(request);
        List<Service> serviceList = serviceService.getAllServices();
        request.setAttribute("serviceList", serviceList);

        TariffService tariffService = new TariffService(request);
        List<Tariff> tariffList = tariffService.getTariffsSortedByABC("DESC");
        request.setAttribute("tariffList", tariffList);

        if (!"".equals(request.getQueryString())) {
            Map<String, String> query = getQueryMap(request.getQueryString());
            for (Map.Entry attr: query.entrySet()) {
                System.out.println(attr.getValue()+" = "+attr.getKey());
            }
        }


        System.out.println(request.getQueryString());
        request.getRequestDispatcher("tariffs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public Map<String, String> getQueryMap(String query)
    {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params)
        {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
}
