package es.uniovi.eii.ds.macros;

import es.uniovi.eii.ds.commands.*;

public class MacroCommand implements Command {
    private Command command;
    private String[] args;

    public MacroCommand(Command command, String[] args) {
        this.command = command;
        this.args = args;
    }

    @Override
    public void execute(String[] args) {
        command.execute(this.args);
    }

    @Override
    public StringBuilder getText() {
        return command.getText();
    }

    @Override
    public void setText(StringBuilder text) {
        this.command.setText(text);
    }

    @Override
    public boolean isRecordable() {
        return command.isRecordable();
    }
    
}
