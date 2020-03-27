package com.hodcko.depfilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/sayhell")
public class FourthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(((HttpServletRequest) servletRequest).getHeader("Header").equalsIgnoreCase("header")){
            servletResponse.getWriter().println("Your are admin");
            Cookie cookie = new Cookie("admin", "admin");
            cookie.setMaxAge(10);
            ((HttpServletResponse) servletResponse).addCookie(cookie);
        }
        servletResponse.getWriter().println("admin");
        filterChain.doFilter(servletRequest, servletResponse);


    }

    @Override
    public void destroy() {

    }
}
