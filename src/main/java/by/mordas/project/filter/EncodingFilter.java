package by.mordas.project.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/***
 Author: Sergei Mordas
 Date: 20.05.2018
 ***/

@WebFilter(urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")},filterName = "encoding")
public class EncodingFilter implements Filter {
    private static final String ENCODING = "encoding";
    /*Character encoding*/
    private String code;

    public void init(FilterConfig config) {
        code = config.getInitParameter(ENCODING);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        code = null;
    }
}