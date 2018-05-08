package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.AdminLogicImpl;

import java.util.HashMap;

public class UpdateSpecialityCommand implements Command {
    AdminLogic adminLogic=new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        HashMap<String,String> parameters=content.getRequestParameters();
        try {
            HashMap<String,String> errorMap=adminLogic.updateSpeciality(parameters);
            if(errorMap.isEmpty()){


                    router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);
                }
                else {
                    router.setPagePath(PageConstant.PAGE_UPDATE_SPECIALITY);
                    content.setSessionAttribute(ParamConstant.ERROR_MESSAGES,errorMap);
                    content.setSessionAttribute(ParamConstant.SPECIALITY,parameters);
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
