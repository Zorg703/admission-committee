package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class UpdateLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router=new Router();
        HttpSession session = request.getSession();
        String lang=(String) session.getAttribute(ParamConstant.LOCALE);
        String locale =(String) request.getParameter(ParamConstant.LOCALE);
        String pagePath=(String)session.getAttribute(ParamConstant.CURRENT_URL);
        String url=request.getHeader("Referer");

        router.setRouter(Router.RouteType.REDIRECT);
        session.setAttribute(ParamConstant.LOCALE, locale);
        router.setPagePath(pagePath);
        return router;
    }
}
