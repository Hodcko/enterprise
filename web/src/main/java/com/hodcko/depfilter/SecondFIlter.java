package com.hodcko.depfilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/sayhello")
public class SecondFIlter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.getWriter().println("UNF-8 encoded");

        filterChain.doFilter(servletRequest, servletResponse);



    }

    @Override
    public void destroy() {

    }
}
