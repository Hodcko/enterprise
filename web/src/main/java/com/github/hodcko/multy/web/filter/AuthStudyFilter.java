package com.github.hodcko.multy.web.filter;

import com.github.hodcko.multy.model.AuthUser;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/study")
public class AuthStudyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();

        if((session.getAttribute("authUser")!= null) &&
                ( ((AuthUser)session.getAttribute("authUser")).getRole().equalsIgnoreCase("student"))){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/InvalidData.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

