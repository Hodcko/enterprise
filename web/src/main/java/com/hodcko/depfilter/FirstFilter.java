package com.hodcko.depfilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/sayhello")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String name = servletRequest.getParameter("name");

        if((servletRequest.getParameter("name")).equalsIgnoreCase("hodcko")){
            servletResponse.getWriter().println("Your name is, " + name);
            servletResponse.getWriter().println("You are logged in");
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            ((HttpServletResponse) servletResponse).sendRedirect("/web/fine");
        }
        servletResponse.getWriter().println("Have a good day!");
    }
}
