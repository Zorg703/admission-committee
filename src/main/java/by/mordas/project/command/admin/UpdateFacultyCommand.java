package by.mordas.project.command.admin;

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

import java.util.Optional;

public class UpdateFacultyCommand implements Command{
    private static Logger logger= LogManager.getRootLogger();

    private FacultyService facultyService;
    private static final String ERROR_ID="error_id";
    private static final String ERROR_FACULTY_NAME="error_name";

    public UpdateFacultyCommand(){
        facultyService= ServiceFactory.getInstance().getFacultyService();
    }

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String facultyId=content.getRequestParameter(ParamConstant.FACULTY_ID);
        String facultyName=content.getRequestParameter(ParamConstant.FACULTY_NAME);
        try {
            Optional<Faculty> optionalFaculty=facultyService.findFaculty(facultyId);
            if(optionalFaculty.isPresent()){
                Faculty faculty=optionalFaculty.get();
                faculty.setFacultyName(facultyName);
                if(facultyService.updateFaculty(faculty)) {
                    router.setRouter(Router.RouteType.REDIRECT);
                    router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);
                }
                else {
                    router.setPagePath(PageConstant.PAGE_UPDATE_FACULTY);
                    content.setRequestAttribute(ERROR_FACULTY_NAME,facultyName);
                }
            }
            else {
                router.setPagePath(PageConstant.PAGE_UPDATE_FACULTY);
                content.setSessionAttribute(ERROR_ID,facultyId);
                router.setRouter(Router.RouteType.REDIRECT);
            }
        } catch (LogicException e) {
            logger.log(Level.ERROR, e.getMessage());
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }


}
