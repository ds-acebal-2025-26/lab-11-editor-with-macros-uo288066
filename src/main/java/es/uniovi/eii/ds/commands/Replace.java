package es.uniovi.eii.ds.commands;

public class Replace implements Command {
    private StringBuilder text;

    public Replace(StringBuilder text) {
        this.text = text;
    }

    @Override
    public void execute(String[] args) {
        String find = args[0];
		String replace = args[1];
		text = new StringBuilder(text.toString().replace(find, replace));
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
