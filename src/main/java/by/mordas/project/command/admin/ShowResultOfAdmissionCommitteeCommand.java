package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.dto.SpecialityDTO;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.service.FacultyService;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.service.UserService;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowResultOfAdmissionCommitteeCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private SpecialityService specialityService;
    private UserService userService;
    private FacultyService facultyService;
    public ShowResultOfAdmissionCommitteeCommand(){
        specialityService= ServiceFactory.getInstance().getSpecialityService();
        userService=ServiceFactory.getInstance().getUserService();
        facultyService=ServiceFactory.getInstance().getFacultyService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String facultyId=content.getRequestParameter(ParamConstant.FACULTY_ID);
        List<SpecialityDTO> dtoList=new ArrayList<>();

        try {
            Optional<Faculty> optionalFaculty=facultyService.findFaculty(facultyId);
            Optional<List<Speciality>> optionalList=specialityService.findSpecialitiesByFacultyId(facultyId);
            if(optionalFaculty.isPresent() && optionalList.isPresent()){
                List<Speciality> specialityList=optionalList.get();
                for(Speciality speciality:specialityList){
                    SpecialityDTO specialityDTO=new SpecialityDTO(speciality);
                    String specialityID= String.valueOf(speciality.getSpecialityId());
                    Optional<List<User>> optionalUsers=userService.findUsersRegisterOnSpeciality(specialityID);
                    optionalUsers.ifPresent(users -> specialityDTO.setCountRegisterUser(users.size()));
                    specialityDTO.setRegisterEnd(specialityService.checkEndOfSpecialityRegistrationDate(speciality));
                    specialityDTO.setSpecialityFull(specialityDTO.getCountRegisterUser()>=speciality.getRecruitmentPlan());
                    specialityDTO.setPassingScore(userService.definePassingScore(speciality));
                    dtoList.add(specialityDTO);
                }
                content.setRequestAttribute(ParamConstant.FACULTY,optionalFaculty.get());
                content.setRequestAttribute(ParamConstant.DTO_LIST,dtoList);
                router.setPagePath(PageConstant.PAGE_SHOW_STATE_OF_ADMISSION_COMMITTEE);
            }
        } catch (LogicException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }

        return router;
    }
}
