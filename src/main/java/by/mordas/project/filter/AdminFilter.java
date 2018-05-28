package by.mordas.project.filter;

import by.mordas.project.command.*;
import by.mordas.project.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/***
 Author: Sergei Mordas
 Date: 20.05.2018
 ***/
@WebFilter(urlPatterns = "/views/jsp/admin/*")
public class AdminFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        User user=(User)request.getSession().getAttribute(ParamConstant.USER);

            if(user==null){
                request.getRequestDispatcher(PageConstant.PAGE_LOGIN).forward(servletRequest, servletResponse);
                return;
            }
            if(user.getRoleId()!=2){
                request.getSession().setAttribute(ParamConstant.MESSAGE, "Error access!");
                request.getRequestDispatcher(PageConstant.PAGE_ERROR).forward(servletRequest, servletResponse);
            }
            filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
