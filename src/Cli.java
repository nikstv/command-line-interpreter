import anotations.Stateless;
import commands.CommandsMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class Cli implements Runnable {

    private final ConcurrentHashMap<String, String> data;
    private final BufferedReader reader;

    public Cli(ConcurrentHashMap<String, String> data, BufferedReader reader) {
        this.data = data;
        this.reader = reader;
    }

    @Override
    public void run() {
        String cliInput = null;

        System.out.println("Please enter a command: ");

        try {
            cliInput = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        while (!"exit".equals(cliInput)) {

            String[] tokens = cliInput.split("\\s+", 2);
            String command = tokens[0];
            String[] params = tokens[1].split("\\s+");

            try {
                Class<?> commandClass = Class.forName("commands." + CommandsMap.getCommandClassName(command));
                Stateless stateless = commandClass.getAnnotation(Stateless.class);
                Method executeCommandMethods = commandClass.getDeclaredMethod("executeCommand");

                Object commandObject = null;

                if (stateless == null) {
                    Constructor<?> constructor = commandClass.getDeclaredConstructor(ConcurrentHashMap.class, String[].class);
                    commandObject = constructor.newInstance(this.data, params);
                } else {
                    Constructor<?> constructor = commandClass.getDeclaredConstructor(String[].class);
                    commandObject = constructor.newInstance((Object) params);
                }

                executeCommandMethods.invoke(commandObject);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                System.out.println(e.getLocalizedMessage());
                return;
            } catch (ClassNotFoundException e) {
                System.out.println("Command not supported.");
            }

            System.out.println("Please enter a command: ");

            try {
                cliInput = reader.readLine();
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
                return;
            }

        }
    }
}