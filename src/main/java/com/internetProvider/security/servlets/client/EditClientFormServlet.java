package com.internetProvider.security.servlets.client;

import com.internetProvider.aservice.CityService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.City;
import com.internetProvider.model.User;
import com.internetProvider.security.App;
import com.internetProvider.security.CryptoUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditClientServlet", value = "/clientPanel/editClientForm/*")
public class EditClientFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityService cityService = CityService.getInstance(request);
        List<City> cityList = cityService.getAllCities();
        request.setAttribute("cityList", cityList);
        request.getRequestDispatcher("../"+ App.Constants.CLIENT_CREATION_FORM_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        HttpSession session = request.getSession();
        if (action != null) {
            if ("/editProfile".equals(action)) {
                editProfile(request, session);
            }
        }
        response.sendRedirect("/"+App.Constants.CLIENT_PANEL_URL);
    }

    private void editProfile(HttpServletRequest request, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Integer cityId = Integer.valueOf(request.getParameter("cityId"));
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCityId(cityId);

        UserService userService = UserService.getInstance(request);
        userService.updateUser(sessionUser.getId(), user);

        if (!password.isEmpty()) {
            userService.updateUserPassword(sessionUser.getId(), CryptoUtil.getEncryptedPassword(password));
        }
    }
}
