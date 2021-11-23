package com.internetProvider.security.filters;

import com.internetProvider.model.Role;
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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("user"))) {
            User user = (User) session.getAttribute("user");
            if (user.getRole().equals(Role.ADMIN)) {
                req.getRequestDispatcher("WEB-INF/jsp/admin/adminPanel.jsp").forward(req, res);
            } else {
                res.getWriter().println("<h1>You don't have an access to this page...</h1>");
                res.getWriter().println("<a href=\"login.jsp\">Back home!</a>");
                System.out.println("denied");
            }
        } else {
            System.out.println("non reg");
            res.sendRedirect("login.jsp");
        }

    }
}
