package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.CommonLogic;
import by.mordas.project.util.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private CommonLogic commonLogic=new CommonLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user;
        String login=content.getRequestParameter(ParamConstant.LOG_IN);
        String password= content.getRequestParameter(ParamConstant.PASSWORD);
        user=commonLogic.findUserLoginAndPassword(login,password);
        if(user!=null){

            content.setSessionAttribute(ParamConstant.USER,user);
            router.setPagePath(PageConstant.PAGE_MAIN);
        }
        else {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.MESSAGE,ParamConstant.MESSAGE);
            content.setSessionAttribute(ParamConstant.LOGIN_PARAM,content.getRequestParameters());
            router.setPagePath(PageConstant.PAGE_LOGIN);
        }
        return router;
    }
}
