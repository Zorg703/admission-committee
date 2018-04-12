package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.logic.AdminLogic;

public class AddSpecialtyCommand implements Command {
    AdminLogic adminLogic=new AdminLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        Speciality speciality=new Speciality();

        return router;
    }
}
