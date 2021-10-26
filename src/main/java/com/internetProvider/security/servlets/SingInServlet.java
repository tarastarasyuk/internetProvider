package com.internetProvider.security.servlets;

import com.internetProvider.database.ConnectionPoolImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "SingInServlet", value = "/SingInServlet")
public class SingInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = (String) request.getAttribute("pox");
        PrintWriter out = response.getWriter();
        out.println(filter+" sth nmore");
        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection()) {
            out.println("wow, that's database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
