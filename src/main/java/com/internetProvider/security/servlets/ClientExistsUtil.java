package com.internetProvider.security.servlets;

import com.internetProvider.aservice.TariffService;
import com.internetProvider.aservice.UserService;
import com.internetProvider.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClientExistsUtil {
    public static boolean usernameExists(HttpServletRequest request, String exceptUsername) {
        boolean result = false;
        UserService userService = UserService.getInstance(request);
        List<String> listOfNames = userService.getAllUsers().stream().map(User::getUsername).filter(Objects::nonNull).collect(Collectors.toList());
        String username = request.getParameter("username");
        for (String usernameFromList: listOfNames) {
            if (!usernameFromList.equals(exceptUsername)) {
                if (usernameFromList.equals(username)) {
                    result = true;
                    break;
                }
            }
        }
        if (result) {
            request.setAttribute("notUniqueUsername", "Failed! User '"+username+"' already exists!");
        }
        return result;
    }

    public static boolean usernameExists(HttpServletRequest request) {
        return usernameExists(request,null);
    }

    public static boolean emailExists(HttpServletRequest request, String exceptEmail) {
        boolean result = false;
        UserService userService = UserService.getInstance(request);
        List<String> listOfEmails = userService.getAllUsers().stream().map(User::getEmail).filter(Objects::nonNull).collect(Collectors.toList());
        String email = request.getParameter("email");
        for (String emailFromList: listOfEmails) {
            if (!emailFromList.equals(exceptEmail)) {
                if (emailFromList.equals(email)) {
                    result = true;
                    break;
                }
            }
        }
        if (result) {
            request.setAttribute("notUniqueEmail", "Failed! Email '"+email+"' has already been taken!");
        }
        return result;
    }

    public static boolean emailExists(HttpServletRequest request) {
        return emailExists(request, null);
    }
}
