package com.internetProvider.security.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

@WebFilter(filterName = "LocaleFilter", urlPatterns = "/*")
public class LocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter locale works");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        clearPageCache(res);
        Locale currentLocale = new Locale("fr","FR");

        boolean localeExistsInCookie = checkCookieExistence("locale", req);

        if (!localeExistsInCookie) {
            Cookie cookie = new Cookie("locale", currentLocale.getLanguage() + "_" + currentLocale.getCountry());
            res.addCookie(cookie);
        }
        chain.doFilter(req, res);
    }

    private void clearPageCache(HttpServletResponse res) {
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0); // Proxies.
    }

    private boolean checkCookieExistence(String cookieName, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void deleteCookie(String cookieName, HttpServletRequest req) {
        Cookie currentCookie = Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .get();
        currentCookie.setMaxAge(0);
        currentCookie.setValue("");
    }


}
