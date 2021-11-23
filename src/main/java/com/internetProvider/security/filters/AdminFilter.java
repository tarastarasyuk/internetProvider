package com.internetProvider.security.filters;

import com.internetProvider.model.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/adminPanel"})
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();
        if (!nonNull(session.getAttribute("user"))) {
            res.sendRedirect("login");
        } else {
            User userAdmin = (User) session.getAttribute("user");
            if (userAdmin.getRoleId() == 1) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("error.jsp");
            }
        }
    }
}
