import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        Cli cli = new Cli(concurrentHashMap, consoleReader);

        cli.run();
    }
}