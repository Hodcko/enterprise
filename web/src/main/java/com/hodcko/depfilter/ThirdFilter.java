package com.hodcko.depfilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/sayhello")
public class ThirdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Cookie cookie = new Cookie("id", "123");
        cookie.setMaxAge(10);
        ((HttpServletResponse) servletResponse).addCookie(cookie);
        ((HttpServletResponse) servletResponse).setHeader("Header", "Header");

        servletResponse.getWriter().println("Cookie added");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
