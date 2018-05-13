package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindUserCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private AdminLogicImpl adminLogicImpl =new AdminLogicImpl();
    private final static String USER_FIND="user_find";

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String userId=content.getRequestParameter(ParamConstant.USER_ID);
            try {
               User user= adminLogicImpl.findUserById(userId);
                if(user!=null) {
                    Speciality speciality=adminLogicImpl.findSpeciality(String.valueOf(user.getSpecialityId()));
                    content.setRequestAttribute(USER_FIND, user);
                    content.setRequestAttribute(ParamConstant.SPECIALITY,speciality);
                    router.setPagePath(PageConstant.PAGE_FIND_USER_BY_ID);
                }
                else {
                    content.setRequestAttribute(ParamConstant.MESSAGE,userId);
                    router.setPagePath(PageConstant.PAGE_FIND_USER_BY_ID);
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
