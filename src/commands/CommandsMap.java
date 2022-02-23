package commands;

import java.util.Map;

public class CommandsMap {
    private final static Map<String, String> commands = Map.of(
            "get", "GetCommand",
            "load", "LoadCommand",
            "save", "SaveCommand",
            "set", "SetCommand",
            "count-words", "CountWords",
            "reverse", "Reverse"
    );

    public static String getCommandClassName(String command){
        String className = commands.get(command);
        if (className!=null){
            return className;
        } else {
            return "";
        }
    }
}