package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.AdminLogicImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowRegisterOnSpecialityUsers implements Command {
    private static Logger logger= LogManager.getRootLogger();

    private AdminLogicImpl adminLogic=new AdminLogicImpl();
   //private static final String LIST_SIZE="list_size";
    @Override
    public Router execute(SessionRequestContent content) {
        Router router = new Router();
        String specialityId = content.getRequestParameter(ParamConstant.SPECIALITY_ID);
        try {
            List<User> users=adminLogic.findUsersRegisterOnSpeciality(specialityId);

            if(!users.isEmpty()){
                Speciality speciality=adminLogic.findSpeciality(specialityId);
                Faculty faculty=adminLogic.findFacultyOnSpeciality(speciality);
                content.setRequestAttribute(ParamConstant.FACULTY,faculty);
                content.setRequestAttribute(ParamConstant.SPECIALITY,speciality);
                content.setRequestAttribute(ParamConstant.USER_LIST,users);
              //  content.setRequestAttribute(LIST_SIZE,users.size());
                router.setPagePath(PageConstant.PAGE_FIND_ALL_USERS_REGISTER_ON_SPECIALITY);
            }
            else {
                router.setPagePath(PageConstant.PAGE_FIND_ALL_USERS_REGISTER_ON_SPECIALITY);
                content.setRequestAttribute(ParamConstant.MESSAGE,specialityId);
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
