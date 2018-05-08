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

public class UpdateFacultyCommand implements Command{
    private AdminLogic adminLogic=new AdminLogicImpl();
    private static final String ERROR_ID="error_id";
    private static final String ERROR_FACULTY_NAME="error_name";

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String facultyId=content.getRequestParameter(ParamConstant.FACULTY_ID);
        String facultyName=content.getRequestParameter(ParamConstant.FACULTY_NAME);
        try {
            Faculty faculty=adminLogic.findFaculty(facultyId);
            if(faculty!=null && faculty.getFacultyName()!=null){
                faculty.setFacultyName(facultyName);
                if(adminLogic.updateFaculty(faculty)) {
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
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }


}
