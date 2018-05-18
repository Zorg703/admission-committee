package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class AddSpecialtyCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private AdminLogicImpl adminLogicImpl =new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        HashMap<String,String> parameters=content.getRequestParameters();
        try {
        HashMap<String,String> errorMessages=adminLogicImpl.addSpeciality(parameters);
        if(errorMessages.isEmpty()){
            router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);
            router.setRouter(Router.RouteType.REDIRECT);
        }
        else {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.ERROR_MESSAGES,errorMessages);
            content.setSessionAttribute(ParamConstant.SPECIALITY,parameters);
            router.setPagePath(PageConstant.PAGE_ADD_SPECIALITY);
        }
        } catch (LogicException e) {
            logger.log(Level.ERROR,e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
