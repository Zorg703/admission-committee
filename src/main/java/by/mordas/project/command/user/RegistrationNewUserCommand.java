package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.impl.UserLogicImpl;
import by.mordas.project.util.Validator;


import java.sql.Date;
import java.util.HashMap;

public class RegistrationNewUserCommand implements Command {
    private UserLogicImpl userLogicImpl =new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        HashMap<String,String> parameters=content.getRequestParameters();
        HashMap<String,String> errorMessages=new Validator().checkUserData(parameters);
        if(errorMessages.isEmpty()) {
            User user=new User();
            user.setFirstName(parameters.get(ParamConstant.FIRST_NAME));
            user.setLastName(parameters.get(ParamConstant.LAST_NAME));
            user.setCertificateMark(Integer.parseInt(parameters.get(ParamConstant.CERTIFICATE_AVG)));
            user.setBirthday(Date.valueOf(parameters.get(ParamConstant.BIRTHDAY)));
            user.setEmail(parameters.get(ParamConstant.EMAIL));
            user.setPassword(parameters.get(ParamConstant.PASSWORD_ONE));
            user.setLogin(parameters.get(ParamConstant.LOG_IN));
            user.put(Integer.valueOf(parameters.get(ParamConstant.FIRST_SUBJECT)), Integer.valueOf(parameters.get(ParamConstant.FIRST_SUBJECT_MARK)));
            user.put(Integer.valueOf(parameters.get(ParamConstant.SECOND_SUBJECT)),Integer.valueOf(parameters.get(ParamConstant.SECOND_SUBJECT_MARK)));
            user.put(Integer.valueOf(parameters.get(ParamConstant.THIRD_SUBJECT)), Integer.valueOf(parameters.get(ParamConstant.THIRD_SUBJECT_MARK)));
            userLogicImpl.registerUser(user);
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
