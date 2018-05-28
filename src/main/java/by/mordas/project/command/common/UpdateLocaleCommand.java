package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/***
 Author: Sergei Mordas
 Date: 15.05.2018
 ***/
public class UpdateLocaleCommand implements Command {

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String locale = content.getRequestParameter(ParamConstant.LOCALE);
        String url=content.getHeader(ParamConstant.REFERER);
        router.setRouter(Router.RouteType.REDIRECT);
        content.setSessionAttribute(ParamConstant.LOCALE, locale);
        router.setPagePath(url);
        return router;
    }
}
