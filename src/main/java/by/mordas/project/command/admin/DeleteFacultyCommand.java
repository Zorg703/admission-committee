package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.Validator;

public class DeleteFacultyCommand implements Command {

    AdminLogic adminLogic=new AdminLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String id=content.getRequestParameter(ParamConstant.FACULTY_ID);
        try {
            if(new Validator().checkid(id) && adminLogic.deleteFaculty(Integer.valueOf(id))){
               router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);

            }
            else {
                router.setRouter(Router.RouteType.REDIRECT);
                router.setPagePath(PageConstant.PAGE_DELETE_FACULTY);
                content.setSessionAttribute(ParamConstant.MESSAGE,ParamConstant.MESSAGE);
            }
        } catch (LogicException e) {
            e.printStackTrace();
        }
        return router;
    }
}
