package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.dto.SpecialityDTO;
import by.mordas.project.dto.UserDTO;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.service.*;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 13.05.2018
 ***/
public class ShowUserStatusCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;
    private SpecialityService specialityService;
    private FacultyService facultyService;

    public ShowUserStatusCommand(){
        specialityService=ServiceFactory.getInstance().getSpecialityService();
        userService=ServiceFactory.getInstance().getUserService();
        facultyService=ServiceFactory.getInstance().getFacultyService();
    }

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user=(User)content.getSessionAttribute(ParamConstant.USER);
            long specialityId = user.getSpecialityId();
            try {
                Optional<Speciality> optionalSpeciality=specialityService.findSpeciality(specialityId);
                if(optionalSpeciality.isPresent()){
                    Speciality speciality=optionalSpeciality.get();
                    SpecialityDTO specialityDTO=new SpecialityDTO(speciality);
                    String specialityID= String.valueOf(speciality.getSpecialityId());
                    Optional<List<User>> optionalUsers=userService.findUsersRegisterOnSpeciality(specialityID);
                    optionalUsers.ifPresent(users -> specialityDTO.setCountRegisterUser(users.size()));
                    specialityDTO.setRegisterEnd(specialityService.checkEndOfSpecialityRegistrationDate(speciality));
                    specialityDTO.setSpecialityFull(specialityDTO.getCountRegisterUser()>=speciality.getRecruitmentPlan());
                    specialityDTO.setPassingScore(userService.definePassingScore(speciality));
                    UserDTO userDTO=new UserDTO();
                    userDTO.setUser(user);
                    userDTO.setSumScores(userService.calculateUserScore(userDTO.getUser()));
                    if(specialityDTO.isRegisterEnd()) {
                        userDTO.setAccepted(userService.isAccepted(speciality, user));
                    }
                    long facultyId = speciality.getFacultyId();
                    Optional<Faculty> optionalFaculty=facultyService.findFaculty(facultyId);
                    optionalFaculty.ifPresent(faculty -> content.setRequestAttribute(ParamConstant.FACULTY, faculty));
                    content.setRequestAttribute(ParamConstant.DTO_SPECIALITY, specialityDTO);
                    content.setRequestAttribute(ParamConstant.DTO_USER,userDTO);
                    router.setPagePath(PageConstant.PAGE_SHOW_USER_STATUS);
                }
                else {
                    router.setPagePath(PageConstant.PAGE_SHOW_USER_STATUS);
                    content.setRequestAttribute(ParamConstant.MESSAGE,specialityId);
                }
            }catch (LogicException e) {
                logger.log(Level.ERROR,e.getMessage());
                router.setRouter(Router.RouteType.REDIRECT);
                content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE, e.getMessage());
                router.setPagePath(PageConstant.PAGE_ERROR);
            }

        return router;
    }
}
