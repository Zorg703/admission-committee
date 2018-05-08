package by.mordas.project.filter;


import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "ControllerFilter", urlPatterns = "/controller")
public class ControllerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute(ParamConstant.USER);
        if(user!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            request.getRequestDispatcher(PageConstant.PAGE_MAIN).forward(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
