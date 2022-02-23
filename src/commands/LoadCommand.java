package commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class LoadCommand extends CliCommandImpl {
    private BufferedReader reader;
    private SetCommand SetCommand;

    public LoadCommand(ConcurrentHashMap<String, String> data, String[] params) {
        super(data, params);
    }

    @Override
    public void executeCommand() {
        try {
            this.reader = new BufferedReader(new FileReader(params[0]));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        String line;
        try {
            line = reader.readLine();
            while (line != null) {

                String[] tokens = line.split("=");
                String key = tokens[0];
                String value = tokens[1];

                SetCommand = new SetCommand(super.data, new String[]{key, value});
                SetCommand.executeCommand();

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        System.out.println("Data from " + params[0] + " is loaded");
    }
}