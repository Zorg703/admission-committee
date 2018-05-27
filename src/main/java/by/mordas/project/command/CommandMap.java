package by.mordas.project.command;

import by.mordas.project.command.admin.*;
import by.mordas.project.command.admin.navigation.ToNextFindUsersPage;
import by.mordas.project.command.common.LogOutCommand;
import by.mordas.project.command.common.LogInCommand;
import by.mordas.project.command.common.RegistrationNewUserCommand;
import by.mordas.project.command.common.UpdateLocaleCommand;
import by.mordas.project.command.gotopage.*;
import by.mordas.project.command.user.*;

import java.util.EnumMap;
import java.util.Optional;

public class CommandMap {
    private EnumMap<CommandType,Command> map=new EnumMap<CommandType, Command>(CommandType.class){{
        this.put(CommandType.SHOW_ALL_USERS,new ShowAllUserCommand());
        this.put(CommandType.LOGIN,new LogInCommand());
        this.put(CommandType.UPDATE_LOCALE,new UpdateLocaleCommand());
        this.put(CommandType.SHOW_ALL_FACULTY,new ShowFacultyCommand());
        this.put(CommandType.FIND_SPECIALITY,new ShowSpecialitiesCommand());
        this.put(CommandType.USER_REGISTRATION,new RegistrationNewUserCommand());
        this.put(CommandType.FIND_USER_SUBJECTS,new ShowUserSubjectsCommand());
        this.put(CommandType.CHANGE_USER_PASSWORD,new ChangePasswordCommand());
        this.put(CommandType.CHOOSE_SPECIALITY,new FindSpecialitySubjectsCommand());
        this.put(CommandType.REGISTER_ON_SPECIALITY,new RegisterOnSpecialityCommand());
        this.put(CommandType.ADD_FACULTY,new AddFacultyCommand());
        this.put(CommandType.DELETE_FACULTY,new DeleteFacultyCommand());
        this.put(CommandType.ADD_SPECIALITY,new AddSpecialtyCommand());
        this.put(CommandType.FIND_USER_BY_ID,new FindUserCommand());
        this.put(CommandType.FIND_ALL_FACULTY,new FindAllFacultyCommand());
        this.put(CommandType.DELETE_SPECIALITY,new DeleteSpecialityCommand());
        this.put(CommandType.SHOW_ALL_SPECIALITIES,new FindAllSpecialityCommand());
        this.put(CommandType.SHOW_ALL_USERS_REGISTER_ON_SPECIALITY,new ShowRegisterOnSpecialityUsers());
        this.put(CommandType.SHOW_USER_STATUS,new ShowUserStatusCommand());
        this.put(CommandType.UPDATE_FACULTY,new UpdateFacultyCommand());
        this.put(CommandType.UPDATE_SPECIALITY,new UpdateSpecialityCommand());
        this.put(CommandType.SHOW_ACCEPTED_USERS,new ShowAcceptedUsersCommand());
        this.put(CommandType.UPDATE_SPECIALITY_REGISTER_DATE,new UpdateSpecialityRegistrationDateCommand());
        this.put(CommandType.LOG_OUT,new LogOutCommand());
        this.put(CommandType.GO_TO_REGISTER_FACULTY_PAGE,new GoToRegisterFacultyPage());
        this.put(CommandType.GO_TO_CHANGE_PASSWORD_PAGE,new GoToChangePasswordPage());
        this.put(CommandType.GO_TO_MAIN_PAGE,new GoToMainPage());
        this.put(CommandType.GO_TO_USER_DATA_PAGE,new GoToUserDataPage());
        this.put(CommandType.GO_TO_USER_STATUS_PAGE,new GoToUserStatusPage());
        this.put(CommandType.GO_TO_UPDATE_FACULTY_PAGE,new GoToUpdateFacultyPage());
        this.put(CommandType.GO_TO_UPDATE_SPECIALITY_PAGE,new GoToUpdateSpecialityPage());
        this.put(CommandType.GO_TO_DELETE_FACULTY_PAGE,new GoToDeleteFacultyPage());
        this.put(CommandType.GO_TO_DELETE_SPECIALITY_PAGE,new GoToDeleteSpecialityPage());
        this.put(CommandType.GO_TO_FIND_USER_BY_ID_PAGE,new GoToFindUserByIdPage());
        this.put(CommandType.GO_TO_CHOOSE_FACULTY_AND_SPECIALITY_PAGE,new GoToChooseFacultyAndSpecialityPage());
        this.put(CommandType.FIND_SPECIALITIES_FOR_CHOOSE_FACULTY,new FindSpecialitiesOnChooseFacultyCommand());
        this.put(CommandType.GO_TO_ADD_FACULTY_PAGE,new GoToAddFacultyPage());
        this.put(CommandType.GO_TO_ADD_SPECIALITY_PAGE,new GoToAddSpecialityPage());
        this.put(CommandType.GO_TO_FIND_ALL_FACULTY_PAGE,new GoToFindAllFacultyPage());
        this.put(CommandType.CANCEL_REGISTRATION,new CancelRegistrationCommand());
        this.put(CommandType.GO_TO_CANCEL_REGISTRATION_PAGE,new GoToCancelRegistrationPage());
        this.put(CommandType.SHOW_RESULT_OF_ADMISSION_COMMITTEE,new ShowResultOfAdmissionCommitteeCommand());
        this.put(CommandType.NEXT_FIND_USERS_PAGE,new ToNextFindUsersPage());
        this.put(CommandType.GO_TO_CHOOSE_FACULTY_TO_RESULT,new GoToChooseFacultyToResultPage());
        this.put(CommandType.GO_TO_CHANGE_SPECIALITY_REGISTER_DATE_PAGE,new GoToChangeRegisterDatePage());
        this.put(CommandType.SHOW_USER_SUBJECTS,new ShowUserSubjectsCommand());
    }
    };
    private static CommandMap instance=new CommandMap();//????
    private CommandMap() {
    }
    public static CommandMap getInstance() {
        return instance;
    }
    public Command get(String cmd){
        try {
            CommandType key = CommandType.valueOf(CommandType.class, cmd.toUpperCase());
            Command command=map.get(key);
            Optional<Command> optionalCommand=Optional.ofNullable(command);
            return optionalCommand.orElseGet(this::getOrDefault);
        }
        catch (IllegalArgumentException e){
            return getOrDefault();
        }

    }
    public Command get(CommandType key){
        return map.get(key);
    }
private Command getOrDefault(){

    return map.get(CommandType.GO_TO_MAIN_PAGE);
}

}
