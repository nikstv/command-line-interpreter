package commands;

import java.util.concurrent.ConcurrentHashMap;

public class SetCommand extends CliCommandImpl {
    public SetCommand(ConcurrentHashMap<String, String> data, String[] param) {
        super(data, param);
    }

    @Override
    public void executeCommand() {
        String key = params[0];
        String value = params[1];
        super.data.put(key, value);
        System.out.println(("Saved " + key + " = " + value));
    }
}
