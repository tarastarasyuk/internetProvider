package com.internetProvider.security.servlets;

import com.internetProvider.aservice.UserService;

import javax.servlet.http.HttpServletRequest;

public class ClientExistsUtil {
    public static boolean usernameExists(HttpServletRequest request) {
        boolean result = false;
        String username = request.getParameter("username");
        UserService userService = UserService.getInstance(request);
        if (userService.checkUserExistenceByUsername(username)) {
            result = true;
            request.setAttribute("notUniqueUsername", "Failed! Such a user already exists!");
        }
        return result;
    }

    public static boolean emailExists(HttpServletRequest request) {
        boolean result = false;
        String email = request.getParameter("email");
        UserService userService = UserService.getInstance(request);
        if (userService.checkUserExistenceByEmail(email)) {
            result = true;
            request.setAttribute("notUniqueEmail", "Failed! The email has already been taken!");
        }
        return result;
    }
}
