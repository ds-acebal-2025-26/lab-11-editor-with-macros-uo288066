package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.macros.MacroHandler;

public class Execute implements Command {
    private MacroHandler mh;
    private StringBuilder text;

    public Execute(MacroHandler mh, StringBuilder text) {
        this.mh = mh;
        this.text = text;
    }

    @Override
    public void execute(String[] args) {
        String macroName = args[0];
		this.text = mh.executeMacro(macroName, args, text);
    }

    @Override
    public void setText(StringBuilder text) {
        this.text = text;
    }

    @Override
    public StringBuilder getText() {
        return this.text;
    }

    @Override
    public boolean isRecordable() {
        return false;
    }
    
}
