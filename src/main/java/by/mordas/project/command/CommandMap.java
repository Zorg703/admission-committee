package by.mordas.project.command;

import by.mordas.project.command.admin.ShowAllUserCommand;

import java.util.EnumMap;

public class CommandMap {
    private EnumMap<CommandType,Command> map=new EnumMap<CommandType, Command>(CommandType.class){{
        this.put(CommandType.SHOW_ALL_USERS,new ShowAllUserCommand());
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
            return null;
        }

    }
    public Command get(CommandType key){
        return map.get(key);
    }

    public Command getOrDefault(Command key,Command defaultValue){
        return map.getOrDefault(key,defaultValue);
    }


}
