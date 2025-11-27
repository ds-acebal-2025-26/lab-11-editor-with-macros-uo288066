package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.macros.MacroHandler;

public class Stop implements Command {
    private MacroHandler mh;

    public Stop(MacroHandler mh) {
        this.mh = mh;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Macro " + mh.getCurrentMacro().getName() + " recorded.");
		mh.stopRecording();
    }

    @Override
    public StringBuilder getText() {
        return new StringBuilder();
    }

    @Override
    public void setText(StringBuilder text) {
        mh.getCurrentMacro().setText(text);
    }

    @Override
    public boolean isRecordable() {
        return true;
    }
    
}
