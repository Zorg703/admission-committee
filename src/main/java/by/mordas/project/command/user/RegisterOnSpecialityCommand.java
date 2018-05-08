package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.UserLogicImpl;
import by.mordas.project.util.DataValidator;

import java.util.HashMap;

public class RegisterOnSpecialityCommand implements Command {
    private UserLogicImpl userLogicImpl =new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        HashMap<String,String> parameters=content.getRequestParameters();
        Long specialityId=Long.valueOf((String) content.getSessionAttribute(ParamConstant.SPECIALITY_ID));
        User user=(User) content.getSessionAttribute(ParamConstant.USER);
        //user.getSpecialityId==0 ???  проверка


        try {
            if(userLogicImpl.setUserSpeciality(user,specialityId,parameters)!=null){
                router.setRouter(Router.RouteType.REDIRECT);
                router.setPagePath(PageConstant.PAGE_USER_SUCCESS);
                content.setSessionAttribute(ParamConstant.USER,user);
            }
            else {
                router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
                content.setSessionAttribute(ParamConstant.MESSAGE,ParamConstant.MESSAGE);
                router.setRouter(Router.RouteType.REDIRECT);
            }
        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
