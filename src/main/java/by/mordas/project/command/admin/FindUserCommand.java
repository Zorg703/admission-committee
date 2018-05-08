package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.LogicException;

public class FindUserCommand implements Command {
    private AdminLogicImpl adminLogicImpl =new AdminLogicImpl();
    private final static String USER_FIND="user_find";

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String userId=content.getRequestParameter(ParamConstant.USER_ID);
            try {
               User user= adminLogicImpl.findUserById(userId);
                if(user!=null) {
                    content.setRequestAttribute(USER_FIND, user);
                    router.setPagePath(PageConstant.PAGE_FIND_USER_BY_ID);
                }
                else {
                    content.setRequestAttribute(ParamConstant.MESSAGE,userId);
                    router.setPagePath(PageConstant.PAGE_FIND_USER_BY_ID);
                }
            } catch (LogicException e) {
                router.setRouter(Router.RouteType.REDIRECT);
                content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
                router.setPagePath(PageConstant.PAGE_ERROR);

            }


        return router;
    }
}
