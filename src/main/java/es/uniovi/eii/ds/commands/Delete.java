package es.uniovi.eii.ds.commands;

public class Delete implements Command {
    private StringBuilder text;

    public Delete(StringBuilder text) {
        this.text = text;
    }

    @Override
    public void execute(String[] args) {  
        int indexOfLastWord = text.toString().trim().lastIndexOf(" ");
		if (indexOfLastWord == -1)
			text = new StringBuilder("");
		else
			text.setLength(indexOfLastWord);
    }

    public StringBuilder getText() {
        return this.text;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }

    @Override
    public boolean isRecordable() {
        return true;
    }
    
}
