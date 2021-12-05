package com.internetProvider.controller;

import com.internetProvider.aservice.UserService;
import com.internetProvider.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "PaymentServlet", value = "/payment")
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        topUpAccount(request, user);

        UserService userService = new UserService(request);
        User updatedUser = userService.getUserByID(user.getId());
        session.removeAttribute("user");
        session.setAttribute("user", updatedUser);
        response.sendRedirect("/clientPanel");
    }

    private void topUpAccount(HttpServletRequest request, User sessionUser) {
        BigDecimal currentAccount= sessionUser.getAccount();
        BigDecimal addedAccount = new BigDecimal(request.getParameter("sum"));
        BigDecimal newAccount = currentAccount.add(addedAccount);

        UserService userService = new UserService(request);
        userService.changeUserAccountById(sessionUser.getId(), newAccount);
    }
}