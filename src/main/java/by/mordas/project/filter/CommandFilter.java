package by.mordas.project.filter;

import by.mordas.project.command.ClientType;
import by.mordas.project.command.CommandType;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/controller")
public class CommandFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        servletRequest.setCharacterEncoding("utf-8");
        try {
            String cmd = request.getParameter(ParamConstant.COMMAND);
            if (cmd != null) {
                CommandType command = CommandType.valueOf(cmd.toUpperCase());
                User user = (User) request.getSession().getAttribute(ParamConstant.USER);
                switch (command.getType()) {
                    case USER:
                        if (user != null && user.getRoleId() == 1) {
                            filterChain.doFilter(servletRequest, servletResponse);
                            break;
                        } else {
                            request.getSession().setAttribute(ParamConstant.MESSAGE, "Error access!");
                            servletRequest.getServletContext().getRequestDispatcher(PageConstant.PAGE_ERROR).forward(servletRequest, servletResponse);
                            break;
                        }
                    case ADMIN:
                        if (user != null && user.getRoleId() == 2) {
                            filterChain.doFilter(servletRequest, servletResponse);
                            break;
                        } else {
                            request.getSession().setAttribute(ParamConstant.MESSAGE, "Error access!");
                            servletRequest.getServletContext().getRequestDispatcher(PageConstant.PAGE_ERROR).forward(servletRequest, servletResponse);
                            break;
                        }
                    case GUEST:
                        filterChain.doFilter(servletRequest, servletResponse);
                        break;
                    default:
                        request.getSession().setAttribute(ParamConstant.MESSAGE, "Error access!");
                        servletRequest.getServletContext().getRequestDispatcher(PageConstant.PAGE_ERROR).forward(servletRequest, servletResponse);
                        break;
                }
            } else {
                request.getSession().setAttribute(ParamConstant.MESSAGE, "Unknown command!");
                servletRequest.getServletContext().getRequestDispatcher(PageConstant.PAGE_ERROR).forward(servletRequest, servletResponse);
            }
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute(ParamConstant.MESSAGE, "Unknown command!");
            servletRequest.getServletContext().getRequestDispatcher(PageConstant.PAGE_ERROR).forward(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
