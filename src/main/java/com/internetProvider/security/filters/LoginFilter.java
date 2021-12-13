package com.internetProvider.security.filters;

import com.internetProvider.aservice.UserService;
import com.internetProvider.model.User;
import com.internetProvider.security.App;
import com.internetProvider.security.CryptoUtil;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;


@WebFilter(filterName = "LoginFilter", urlPatterns = {"/clientPanel/*"})
public class LoginFilter implements Filter {
    private final static Logger logger = Logger.getLogger(LoginFilter.class);

    public void init(FilterConfig config) throws ServletException {
        System.out.println("FILTER WORKS");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = UserService.getInstance(req);
        HttpSession session = req.getSession();

        // checking user for existing in session
        if (nonNull(session) && nonNull(session.getAttribute(App.Constants.SESSION_USER))) {
            User user = (User) session.getAttribute(App.Constants.SESSION_USER);

            // checking users roles and then redirecting to central servlets of each entity
            if (user.getRoleId() == App.Constants.ADMIN_ROLE_ID) {
                res.sendRedirect(App.Constants.ADMIN_PANEL_URL);
            } else
                chain.doFilter(req, res);

        } else if (nonNull(username) && nonNull(password)) {
            // checking user for existence
            if (userService.checkUserExistenceByUsername(username)) {
                // checking existed user for correct password
                User existingUser = userService.findUserByUsernameAndPassword(username, CryptoUtil.getEncryptedPassword(password));

                if (existingUser != null) {
                    session.setAttribute(App.Constants.SESSION_USER, existingUser);
                    // redirecting users by role id
                    redirectUser(existingUser, session, res);
                } else {
                    redirectToLoginWithMessage("Incorrect data: password is incorrect", session, res);
                }
            } else {
                redirectToLoginWithMessage("Incorrect data: no such user", session, res);
            }

        } else {
            res.sendRedirect("/"+App.Constants.LOGIN_URL);
        }
    }

    private void redirectToLoginWithMessage(String msg, HttpSession session, HttpServletResponse res) throws IOException {
        logger.info(msg);
        session.setAttribute("signInError", msg);
        res.sendRedirect("/"+App.Constants.LOGIN_URL);
    }

    private void redirectUser(User existingUser, HttpSession session, HttpServletResponse res) throws IOException {
        logger.info(existingUser.getRole() + " logged in");
        switch (existingUser.getRoleId()) {
            case 1:
                session.setAttribute("pattern", App.Constants.ADMIN_PANEL_URL);
                res.sendRedirect("/"+App.Constants.ADMIN_PANEL_URL);
                break;
            case 2:
                session.setAttribute("pattern", App.Constants.CLIENT_PANEL_URL);
                res.sendRedirect("/"+App.Constants.CLIENT_PANEL_URL);
                break;
            default:
                break;
        }
    }
}
