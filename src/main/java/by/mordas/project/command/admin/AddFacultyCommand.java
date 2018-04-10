package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.Validator;

public class AddFacultyCommand implements Command {
    AdminLogic adminLogic=new AdminLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String facultyName=content.getRequestParameter(ParamConstant.FACULTY_NAME);
        if(new Validator().checkFacultyName(facultyName)) {
            try {
                Faculty faculty = adminLogic.addFaculty(facultyName);
                content.setSessionAttribute(ParamConstant.FACULTY,faculty);
                router.setRouter(Router.RouteType.REDIRECT);
                router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);
            } catch (LogicException e) {
                e.printStackTrace();
            }
        }
        else {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.FACULTY_NAME,facultyName);
            router.setPagePath(PageConstant.PAGE_ADD_FACULTY);
        }
        return router;
    }
}
