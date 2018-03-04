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
        HttpSession session=request.getSession();
        Router router=new Router();
        String locale=request.getParameter(ParamConstant.LOCALE);
        session.setAttribute(ParamConstant.LOCALE,locale);


        return null;
    }
}
