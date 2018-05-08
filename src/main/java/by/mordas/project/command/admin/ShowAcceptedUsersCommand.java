package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.AdminLogicImpl;

import java.util.List;

public class ShowAcceptedUsersCommand implements Command{
    private AdminLogic adminLogic=new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String specialityId = content.getRequestParameter(ParamConstant.SPECIALITY_ID);
        try {
            List<User> acceptedUsers=adminLogic.findAllAcceptedUsersOnSpeciality(specialityId);
            content.setRequestAttribute(ParamConstant.USER_LIST,acceptedUsers);
            router.setPagePath(PageConstant.PAGE_SHOW_ACCEPTED_USERS);

        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
