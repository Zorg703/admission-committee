package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.Validator;

public class DeleteSpecialityCommand implements Command{
    private AdminLogicImpl adminLogicImpl =new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String specialityId=content.getRequestParameter(ParamConstant.SPECIALITY_ID);
        try {
            if(new Validator().checkId(specialityId) && adminLogicImpl.deleteSpeciality(specialityId)){
              router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);

            }
            else {

                router.setPagePath(PageConstant.PAGE_DELETE_SPECIALITY);
                content.setSessionAttribute(ParamConstant.MESSAGE,ParamConstant.MESSAGE);
            }
        } catch (LogicException e) {
            e.printStackTrace();
        }

        return router;
    }
}
