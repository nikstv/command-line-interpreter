package commands;

import anotations.Stateless;

@Stateless
public class Reverse implements CliCommand {

    private final String[] params;

    public Reverse(String[] params) {
        this.params = params;
    }

    @Override
    public void executeCommand() {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < params.length; i++) {
            if (i == 0) {
                text.append(params[i]);
            } else {
                text.append(" ").append(params[i]);
            }
        }

        text.reverse();
        System.out.println(text);
    }
}