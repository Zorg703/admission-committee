package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.entity.User;
import by.mordas.project.logic.CommonLogic;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    CommonLogic commonLogic=new CommonLogic();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router=new Router();
        User user;
        String login=request.getParameter(ParamConstant.LOG_IN);
        String password=request.getParameter(ParamConstant.PASSWORD);
        user=commonLogic.findUserLoginAndPassword(login,password);
        if(user!=null){
            request.setAttribute(ParamConstant.USER,user);
            router.setPagePath(PageConstant.PAGE_MAIN);
        }
        else {

        }
        return router;
    }
}
