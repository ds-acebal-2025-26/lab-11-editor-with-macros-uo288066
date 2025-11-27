package es.uniovi.eii.ds.macros;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.ds.commands.*;

public class Macro implements Command {
    private String name;
    private List<Command> commands;
    private StringBuilder text;

    public Macro(String name) {
        this.name = name;
        this.commands = new ArrayList<Command>();
        this.text = new StringBuilder();
    }

    public String getName() {
        return name;
    }

    public void execute(String[] args) {
        for (Command command : commands) {
            command.setText(this.text); 
            command.execute(args); 
            this.text = command.getText(); 
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public StringBuilder getText() {
        return text;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }

    @Override
    public boolean isRecordable() {
        return true;
    }
}
