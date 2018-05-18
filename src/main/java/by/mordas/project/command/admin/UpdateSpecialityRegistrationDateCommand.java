package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.AdminLogicImpl;

import java.util.HashMap;

public class UpdateSpecialityRegistrationDateCommand implements Command
{
    private AdminLogic adminLogic=new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String start_registration=content.getRequestParameter(ParamConstant.START_REGISTRATION);
        String end_registration=content.getRequestParameter(ParamConstant.END_REGISTRATION);
        String specialityId=content.getRequestParameter(ParamConstant.SPECIALITY_ID);
        try{
            HashMap<String,String> errorMap=adminLogic.updateRegisterOnSpecialityDate(start_registration,end_registration,specialityId);
            if(errorMap.isEmpty()){

            }
        } catch (LogicException e) {

        }
        return null;
    }
}
