package by.mordas.project.filter;

import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {},initParams = {
        @WebInitParam(name = "locale",value = "ru_RU")
})
public class LangugeFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request=new HttpServletRequestWrapper((HttpServletRequest) servletRequest);
        HttpSession session=request.getSession(true);
        if(request.getParameter("locale")==null || "ru_RU".equals(request.getParameter("locale")) ){
        request.setAttribute(ParamConstant.LOCALE,"en_EN");
        session.setAttribute(ParamConstant.LOCALE,"ru_RU");
        }
        else {
            request.setAttribute(ParamConstant.LOCALE,"ru_RU");
            session.setAttribute(ParamConstant.LOCALE,"en_EN");
        }
        filterChain.doFilter(request,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
