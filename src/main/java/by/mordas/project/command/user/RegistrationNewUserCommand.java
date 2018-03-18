package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.UserLogic;
import by.mordas.project.util.Validator;


import java.sql.Date;
import java.util.HashMap;

public class RegistrationNewUserCommand implements Command {
    private UserLogic userLogic=new UserLogic();
    @Override
    public Router execute(SessionRequestContent content) {


        Router router=new Router();

        HashMap<String,String> parameters=content.getRequestParameters();
        HashMap<String,String> errorMessages=new Validator().checkUserDate(parameters);
        if(errorMessages.isEmpty()) {
            User user=new User();
            user.setFirstName(content.getRequestParameter("first-name"));
            user.setLastName(content.getRequestParameter("last-name"));
            user.setCertificateMark(Integer.parseInt(content.getRequestParameter("avg")));
            user.setBirthday(Date.valueOf(content.getRequestParameter("birthday")));
            user.setEmail(content.getRequestParameter("email"));
            user.setPassword(content.getRequestParameter("password"));
            user.setLogin(content.getRequestParameter("login"));
            user.put(userLogic.findSubject(Integer.valueOf(content.getRequestParameter("first-subject"))), Integer.valueOf(content.getRequestParameter("mark1")));
            user.put(userLogic.findSubject(Integer.valueOf(content.getRequestParameter("second-subject"))), Integer.valueOf(content.getRequestParameter("mark2")));
            user.put(userLogic.findSubject(Integer.valueOf(content.getRequestParameter("third-subject"))), Integer.valueOf(content.getRequestParameter("mark3")));
            userLogic.registerUser(user);
            content.setSessionAttribute(ParamConstant.USER,user);
            router.setRouter(Router.RouteType.REDIRECT);
            router.setPagePath(PageConstant.PAGE_MAIN);
        }

        else {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.USER_PARAMS,parameters);
            content.setSessionAttribute(ParamConstant.ERROR_MESSAGES ,errorMessages);
            router.setPagePath(PageConstant.PAGE_REGISTRATION);
        }


        return router;
    }

}
