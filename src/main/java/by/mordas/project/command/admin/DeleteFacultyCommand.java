package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.service.FacultyService;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.impl.FacultyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/***
 Author: Sergei Mordas
 Date: 06.04.2018
 ***/
public class DeleteFacultyCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private FacultyService facultyService;

    public DeleteFacultyCommand(){
        facultyService=new FacultyServiceImpl();
    }

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String facutyId=content.getRequestParameter(ParamConstant.FACULTY_ID);
        try {
            if(facultyService.deleteFaculty(facutyId)){
               router.setRouter(Router.RouteType.REDIRECT);
               router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);

            }
            else {
               // router.setRouter(Router.RouteType.REDIRECT);
                router.setPagePath(PageConstant.PAGE_DELETE_FACULTY);
                content.setRequestAttribute(ParamConstant.MESSAGE,facutyId);
            }
        } catch (LogicException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
