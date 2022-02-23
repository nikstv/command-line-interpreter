package commands;

import java.util.concurrent.ConcurrentHashMap;

public class GetCommand extends CliCommandImpl {
    public GetCommand(ConcurrentHashMap<String, String> data, String[] param) {
        super(data, param);
    }

    @Override
    public void executeCommand() {
        String key = params[0];
        String value = super.data.get(key);
        if (value == null) {
            System.out.println("Err: no value for " + key);
        } else {
            System.out.println(key + " = " + value);
        }
    }
}