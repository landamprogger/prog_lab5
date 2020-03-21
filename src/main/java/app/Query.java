package app;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class contains all the information about the command, its name, type and arguments.
 */
public final class Query {
    //TODO: Лучше передавать стрингу, а не енам, когда будешь настраивать сервер могут появиться проблемы из-за сложности енама относительно стринги.
    private String commandName;
    private String commandType;
    //TODO: solid
    private HashMap<String, String> arguments;

    public Query(String commandName, String commandType, HashMap<String,String> arguments){
        this.commandName = commandName;
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandType() {
        return commandType;
    }

    public HashMap<String, String> getArguments() {
        return arguments;
    }
}

