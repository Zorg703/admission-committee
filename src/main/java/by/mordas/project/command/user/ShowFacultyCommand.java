package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowFacultyCommand implements Command {
    private UserLogic userLogic=new UserLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        List<Faculty> faculties;
        faculties=userLogic.findAllFaculties();
        content.setRequestAttribute(ParamConstant.FACULTY_LIST,faculties);
        router.setPagePath(PageConstant.PAGE_FIND_ALL_FACULTY);
        return router;
    }
}
