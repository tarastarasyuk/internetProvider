package com.internetProvider.security.servlets;

import com.internetProvider.aservice.ServiceService;
import com.internetProvider.aservice.TariffService;
import com.internetProvider.model.Service;
import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@WebServlet(name = "TariffServlet", value = "/tariffs/*")
public class TariffServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(TariffServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (nonNull(action))
            switch (action) {
                case "/downloadTariff":
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename = tariff.pdf");
                    downloadTariff(request, response);
                    try(InputStream in = new FileInputStream("C:/Programs/Java/internetProvider/src/main/webapp/tariff.pdf");
                        OutputStream out = response.getOutputStream()) {
                        byte[] buffer = new byte[1024];
                        int numBytesRead;
                        while ((numBytesRead = in.read(buffer)) > 0) {
                            out.write(buffer, 0, numBytesRead);
                        }
                    }
                    break;
                default:
                    break;
            } else {

            ServiceService serviceService = ServiceService.getInstance(request);
            List<Service> serviceList = serviceService.getAllServices();
            request.setAttribute("serviceList", serviceList);
            TariffService tariffService = TariffService.getInstance(request);


            List<Tariff> tariffList = new ArrayList<>();

            if (request.getQueryString() != null) {
                String service = request.getParameter("service");
                String sortBy = request.getParameter("sortBy");

                if (service != null && sortBy != null) {
                    int[] services = getServicesId(request.getParameterValues("service"));
                    tariffList = tariffService.getTariffsByServices(services);
                    tariffList = sortTariffBy(tariffList, sortBy);
                    request.setAttribute("selectedServices", Arrays.toString(services));
                    request.setAttribute("selectedSortBy", sortBy);
                } else if (service != null) {
                    int[] services = getServicesId(request.getParameterValues("service"));
                    tariffList = tariffService.getTariffsByServices(services);
                    request.setAttribute("selectedServices", Arrays.toString(services));
                } else if (sortBy != null) {
                    tariffList = tariffService.getAllTariffs();
                    tariffList = sortTariffBy(tariffList, sortBy);
                    request.setAttribute("selectedSortBy", sortBy);
                }

            } else {
                tariffList = tariffService.getAllTariffs();
            }


// --------------SORTING BY SQL
//        if (request.getQueryString() != null) {
//            String service = request.getParameter("service");
//            String sortBy = request.getParameter("sortBy");
//            if (service != null && sortBy != null) {
//                String[] values = sortBy.split("_");
//                int[] services = getServicesId(request.getParameterValues("service"));
//                tariffList = tariffService.getTariffsByServicesSortedBy(services, values[0], values[1]);
//                request.setAttribute("selectedServices", Arrays.toString(services));
//                request.setAttribute("selectedSortBy", sortBy);
//            } else if (service != null) {
//                int[] services = getServicesId(request.getParameterValues("service"));
//                tariffList = tariffService.getTariffsByServices(services);
//                request.setAttribute("selectedServices", Arrays.toString(services));
//            } else if (sortBy != null) {
//                String[] values = sortBy.split("_");
//                tariffList = tariffService.getTariffsSortedBy(values[0], values[1]);
//                request.setAttribute("selectedSortBy", sortBy);
//            } else {
//                tariffList = tariffService.getAllTariffs();
//            }
//
//        } else {
//
//            tariffList = tariffService.getAllTariffs();
////            // for not to be in permanent order after refresh
////            Collections.shuffle(tariffList);
//        }


            if (!tariffList.isEmpty()) {
                request.setAttribute("tariffList", tariffList);
            } else {
                request.setAttribute("noSuchTariffs", "Sorry, there are no such tariffs");
            }


            HttpSession session = request.getSession();
            User user = new User();
            if (nonNull(session)) {
                user = (User) session.getAttribute("user");
            }
            request.setAttribute("user", user);
            request.getRequestDispatcher("tariffs.jsp").forward(request, response);
        }

    }

    private void downloadTariff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer tariffId = Integer.valueOf(request.getParameter("tariff_id_download"));
        TariffService tariffService = TariffService.getInstance(request);
        Tariff tariff = tariffService.getTariffById(tariffId);
        System.out.println(getServletConfig());
        System.out.println(getServletContext());
        System.out.println("===");
        PDFCreatorUtil.createTariff(tariff);
    }

    private List<Tariff> sortTariffBy(List<Tariff> tariffList, String sortBy) {
        switch (sortBy) {
            case "price_asc":
                return tariffList = tariffList.stream()
                        .sorted(Comparator.comparing(Tariff::getPrice))
                        .collect(Collectors.toList());
            case "price_desc":
                return tariffList = tariffList.stream()
                        .sorted(Comparator.comparing(Tariff::getPrice).reversed())
                        .collect(Collectors.toList());
            case "abc_asc":
                return tariffList = tariffList.stream()
                        .sorted(Comparator.comparing(Tariff::getName))
                        .collect(Collectors.toList());
            case "abc_desc":
                return tariffList = tariffList.stream()
                        .sorted(Comparator.comparing(Tariff::getName).reversed())
                        .collect(Collectors.toList());
            default:
                return tariffList;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private int[] getServicesId(String[] values) {
        return Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
    }
}
