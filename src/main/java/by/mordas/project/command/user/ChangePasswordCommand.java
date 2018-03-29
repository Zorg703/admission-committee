package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.UserLogic;
import by.mordas.project.util.Validator;

import java.util.HashMap;

public class ChangePasswordCommand implements Command {
    UserLogic userLogic=new UserLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String password1=content.getRequestParameter(ParamConstant.PASSWORD_ONE);
        String password2=content.getRequestParameter(ParamConstant.PASSWORD_TWO);
        HashMap<String,String> errorMap=new Validator().checkChangedPassword(password1,password2);
        if(errorMap.isEmpty()){
            Integer userID=((User)content.getSessionAttribute(ParamConstant.USER)).getUserId();
            try {
                userLogic.changePassword(userID,password1);
                router.setPagePath(PageConstant.PAGE_USER_SUCCESS);
                router.setRouter(Router.RouteType.REDIRECT);
            } catch (LogicException e) {
                e.printStackTrace();
            }
        }
        else {
            router.setRouter(Router.RouteType.REDIRECT);
            router.setPagePath(PageConstant.PAGE_CHANGE_PASSWORD);
            content.setSessionAttribute(ParamConstant.ERROR_MESSAGES,errorMap);
        }

        return router;
    }
}
