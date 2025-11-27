package es.uniovi.eii.ds.commands;

public class Insert implements Command {
    private StringBuilder text;

    public Insert(StringBuilder text) {
        this.text = text;
    }

    @Override
    public void execute(String[] args) {
        for (String word : args) {
            text.append(" ").append(word);
        }
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
