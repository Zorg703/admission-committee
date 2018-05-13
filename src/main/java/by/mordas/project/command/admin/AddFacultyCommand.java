package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddFacultyCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private AdminLogicImpl adminLogicImpl =new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String facultyName=content.getRequestParameter(ParamConstant.FACULTY_NAME);


            try {
                Faculty faculty = adminLogicImpl.addFaculty(facultyName);
                if(faculty!=null) {
                    router.setRouter(Router.RouteType.REDIRECT);
                    content.setSessionAttribute(ParamConstant.FACULTY, faculty);
                    router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);
                }
                else {
                    content.setSessionAttribute(ParamConstant.MESSAGE, faculty);
                    router.setPagePath(PageConstant.PAGE_ADD_FACULTY);
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
