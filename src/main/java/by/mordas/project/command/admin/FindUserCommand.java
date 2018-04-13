package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.util.Validator;

public class FindUserCommand implements Command {
    AdminLogic adminLogic=new AdminLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String userId=content.getRequestParameter(ParamConstant.USER_ID);
        if(new Validator().checkId(userId)){
           adminLogic.findUserById(userId);
        }
        else {

        }
        return router;
    }
}
