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

@WebFilter(filterName = "AdminFilter", urlPatterns = "/admin_menu")
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
                chain.doFilter(request, response);
//                req.getRequestDispatcher("/admin_menu.jsp");
            } else {
                System.out.println("denied");
            }
        } else {
            System.out.println("non reg");
            res.sendRedirect("/");
        }

    }
}
