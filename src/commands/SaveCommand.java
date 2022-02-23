package commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SaveCommand extends CliCommandImpl {

    private BufferedWriter writer;

    public SaveCommand(ConcurrentHashMap<String, String> data, String[] params) {
        super(data, params);
    }

    @Override
    public synchronized void executeCommand() {
        try {
            this.writer = new BufferedWriter(new FileWriter(params[0], true));
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        for (Map.Entry<String, String> entry : super.data.entrySet()) {
            try {
                writer.write(entry.getKey() + "=" + entry.getValue() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
                return;
            }
        }

        try {
            writer.close();
            System.out.println("Data exported to " + params[0]);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}