package by.mordas.project.command;

import by.mordas.project.command.admin.ShowAllUserCommand;
import by.mordas.project.command.common.LoginCommand;
import by.mordas.project.command.common.UpdateLocaleCommand;
import by.mordas.project.command.user.RegistrationNewUserCommand;
import by.mordas.project.command.user.ShowFacultyCommand;
import by.mordas.project.command.user.ShowSpecialitiesCommand;
import by.mordas.project.command.user.ShowUserSubjectsCommand;

import java.util.EnumMap;

public class CommandMap {
    private EnumMap<CommandType,Command> map=new EnumMap<CommandType, Command>(CommandType.class){{
        this.put(CommandType.SHOW_ALL_USERS,new ShowAllUserCommand());
        this.put(CommandType.LOGIN,new LoginCommand());
        this.put(CommandType.UPDATE_LOCALE,new UpdateLocaleCommand());
        this.put(CommandType.SHOW_ALL_FACULTY,new ShowFacultyCommand());
        this.put(CommandType.FIND_SPECIALITY,new ShowSpecialitiesCommand());
        this.put(CommandType.USER_REGISTRATION,new RegistrationNewUserCommand());
        this.put(CommandType.FIND_USER_SUBJECTS,new ShowUserSubjectsCommand());

    }

    };
    private static CommandMap instance=new CommandMap();
    private CommandMap() {
    }
    public static CommandMap getInstance() {
        return instance;
    }
    public Command get(String cmd){
        try {
            CommandType key = CommandType.valueOf(CommandType.class, cmd.toUpperCase());

            return map.get(key);
        }
        catch (EnumConstantNotPresentException e){
            return null;//todo
        }

    }
    public Command get(CommandType key){
        return map.get(key);
    }

    public Command getOrDefault(Command key,Command defaultValue){
        return map.getOrDefault(key,defaultValue);
    }


}
