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
        String locale = (String) session.getAttribute("locale");
        String s3=(String)session.getAttribute("current");
        if (locale == null || "en_EN".equals(locale)) {
            locale = "ru-RU";
        } else {
            locale = "en_EN";
        }

        router.setRouter(Router.RouteType.REDIRECT);
        session.setAttribute("locale", locale);
        router.setPagePath(s3);

        return router;
    }
}
