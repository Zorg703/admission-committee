package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.service.FacultyService;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowFacultyCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private FacultyService facultyService;

    public ShowFacultyCommand(){
        facultyService=ServiceFactory.getInstance().getFacultyService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        List<Faculty> faculties;
        try {
            faculties= facultyService.findAllFaculties();
            content.setRequestAttribute(ParamConstant.FACULTY_LIST,faculties);
            router.setPagePath(PageConstant.PAGE_SHOW_ALL_FACULTY);
        } catch (LogicException e) {
            logger.log(Level.ERROR,e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }

        return router;
    }
}
