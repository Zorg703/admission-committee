package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.DataValidator;

public class DeleteFacultyCommand implements Command {

    private AdminLogicImpl adminLogicImpl =new AdminLogicImpl();

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String id=content.getRequestParameter(ParamConstant.FACULTY_ID);
        try {
            if(adminLogicImpl.deleteFaculty(id)){
               router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);

            }
            else {
                router.setRouter(Router.RouteType.REDIRECT);
                router.setPagePath(PageConstant.PAGE_DELETE_FACULTY);
                content.setSessionAttribute(ParamConstant.MESSAGE,"Incorrect faculty id");
            }
        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
