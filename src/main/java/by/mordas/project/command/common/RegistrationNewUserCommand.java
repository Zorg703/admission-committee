package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.CommonLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.CommonLogicImpl;
import by.mordas.project.logic.impl.UserLogicImpl;
import by.mordas.project.util.DataValidator;


import java.sql.Date;
import java.util.HashMap;

public class RegistrationNewUserCommand implements Command {
    private UserLogicImpl userLogicImpl =new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        HashMap<String,String> parameters=content.getRequestParameters();
        HashMap<String,String> errorMessages= null;
        try {
            errorMessages = userLogicImpl.registerUser(parameters);
            if(errorMessages.isEmpty()){
                router.setRouter(Router.RouteType.REDIRECT);
                router.setPagePath(PageConstant.PAGE_SUCCESS_REGISTRATION);
            }
            else {
                router.setRouter(Router.RouteType.REDIRECT);
                content.setSessionAttribute(ParamConstant.USER_PARAMS,parameters);
                content.setSessionAttribute(ParamConstant.ERROR_MESSAGES ,errorMessages);
                router.setPagePath(PageConstant.PAGE_REGISTRATION);
            }
        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }





        return router;
    }

}
