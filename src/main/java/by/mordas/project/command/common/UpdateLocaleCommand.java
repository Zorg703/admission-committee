package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class UpdateLocaleCommand implements Command {

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String locale = content.getRequestParameter(ParamConstant.LOCALE);
        String pagePath=(String)content.getSessionAttribute(ParamConstant.CURRENT_URL);
        String url=content.getHeader("Referer");
        router.setRouter(Router.RouteType.REDIRECT);
        content.setSessionAttribute(ParamConstant.LOCALE, locale);
        router.setPagePath(pagePath);
        return router;
    }
}
