package commands;

import anotations.Stateless;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Stateless
public class CountWords implements CliCommand {
    private String[] params;
    private BufferedReader reader;
    private int wordsCounter;
    private boolean isWord;

    public CountWords(String[] params) {
        this.params = params;
        wordsCounter = 0;
        isWord = false;
    }

    @Override
    public void executeCommand() {
        try {
            reader = new BufferedReader(new FileReader(params[0]));
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        try {
            int symbolCode = reader.read();
            while (symbolCode != -1) {
                char symbol = (char) symbolCode;
                if (symbol != ' ' && symbol != '\t' && symbol != '\r' && symbol != '\n' && !isWord) {
                    wordsCounter++;
                    isWord = true;
                } else if (symbol == ' ' || symbol == '\t' || symbol == '\r' || symbol == '\n') {
                    isWord = false;
                }

                symbolCode = reader.read();
            }


        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        System.out.println("Words in " + params[0] + ": " + wordsCounter);
    }
}