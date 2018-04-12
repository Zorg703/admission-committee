package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.util.Validator;

import java.util.HashMap;

public class AddSpecialtyCommand implements Command {
    AdminLogic adminLogic=new AdminLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        Speciality speciality=new Speciality();
        HashMap<String,String> parameters=content.getRequestParameters();
        HashMap<String,String> errorMessages=new Validator().checkSpecialtyData(parameters);
        if(errorMessages.isEmpty()){

        }
        else {

        }
        return router;
    }
}
