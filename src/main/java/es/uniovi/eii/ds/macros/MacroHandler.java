package es.uniovi.eii.ds.macros;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.ds.commands.Command;

public class MacroHandler {
    private List<Macro> macros = new ArrayList<Macro>();
    private Macro currentMacro = null;

    public void addMacro(Macro macro) {
        macros.add(macro);
    }

    public Macro getMacroByName(String name) {
        for (Macro macro : macros) {
            if (macro.getName().equals(name)) {
                return macro;
            }
        }
        return null;
    }
    
    public Macro getCurrentMacro() {
        return currentMacro;
    }

    public void setCurrentMacro(Macro currentMacro) {
        this.currentMacro = currentMacro;
    }

    public void startRecording(String name) {
        currentMacro = new Macro(name);
    }

    public void stopRecording() {
        if (currentMacro != null) {
            macros.add(currentMacro);
        }
        currentMacro = null;
    }

    public StringBuilder executeMacro(String name, String[] args, StringBuilder text) {
        Macro macro = getMacroByName(name);
        if (macro != null) {
            macro.setText(text);
            macro.execute(args);
        }

        return macro.getText();
    }

    public boolean isRecording() {
        return currentMacro != null;
    }

    public void recordCommand(Command cmd, String[] args) {
        if (currentMacro != null) {
            currentMacro.addCommand(new MacroCommand(cmd, args));
        }
    }
}
