package commands;

import java.util.concurrent.ConcurrentHashMap;

public abstract class CliCommandImpl implements CliCommand {
    protected final ConcurrentHashMap<String, String> data;
    protected final String[] params;

    public CliCommandImpl(ConcurrentHashMap<String, String> data, String[] params) {
        this.data = data;
        this.params = params;
    }
}