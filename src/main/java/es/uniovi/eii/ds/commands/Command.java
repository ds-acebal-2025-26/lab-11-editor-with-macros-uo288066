package es.uniovi.eii.ds.commands;

public interface Command {
    public void execute(String[] args);
    public StringBuilder getText();
    public void setText(StringBuilder text);
    public boolean isRecordable();
}
