package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.macros.MacroHandler;

public class Record implements Command {

    private MacroHandler mh;

    public Record(MacroHandler mh) {
        this.mh = mh;
    }

    @Override
    public void execute(String[] args) {
        String macroName = args[0];
		mh.startRecording(macroName);
    }

    @Override
    public void setText(StringBuilder text) {
        
    }

    @Override
    public StringBuilder getText() {
        return mh.getCurrentMacro().getText();
    }

    @Override
    public boolean isRecordable() {
        return false;
    }
}
