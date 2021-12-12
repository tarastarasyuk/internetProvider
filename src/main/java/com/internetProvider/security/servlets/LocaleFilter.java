package com.internetProvider.security.servlets;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

@WebFilter(filterName = "LocaleFilter", urlPatterns = "/fefe")
public class LocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter locale works");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

//        Locale currentLocale = res.getLocale();
        Locale currentLocale = new Locale("uk","UA");
        Arrays.stream(req.getCookies()).filter(innerCookie -> innerCookie.getName().equals("country")).findAny().get();

        Cookie cookieCountry = new Cookie("country", currentLocale.getCountry());
        Cookie cookie = new Cookie("locale", currentLocale.getLanguage()+"_"+currentLocale.getCountry());
        res.addCookie(cookie);
        res.addCookie(cookieCountry);
        chain.doFilter(req, res);
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
