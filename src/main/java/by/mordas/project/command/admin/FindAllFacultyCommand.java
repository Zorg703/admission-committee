package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.AdminLogicImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllFacultyCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private AdminLogic adminLogic=new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        try {
            List<Faculty> faculties=adminLogic.findAllFaculty();
            content.setSessionAttribute(ParamConstant.FACULTY_LIST,faculties);
            router.setPagePath(PageConstant.PAGE_FIND_ALL_FACULTY);

        } catch (LogicException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}