package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Subject;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.UserLogicImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowSpecialitySubjectsCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserLogicImpl userLogicImpl =new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String specialityId= content.getRequestParameter(ParamConstant.SPECIALITY_ID);
        try {
            List<Subject> subjects= userLogicImpl.findSubjectsForSpeciality(specialityId);
            if(!subjects.isEmpty()) {
                router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
                content.setRequestAttribute(ParamConstant.SUBJECTS, subjects);
                content.setSessionAttribute(ParamConstant.SPECIALITY_ID, specialityId);
            }
            else {
                router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
                content.setRequestAttribute(ParamConstant.MESSAGE,specialityId);
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
