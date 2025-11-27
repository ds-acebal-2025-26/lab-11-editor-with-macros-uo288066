package es.uniovi.eii.ds.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import es.uniovi.eii.ds.commands.*;
import es.uniovi.eii.ds.commands.Record;
import es.uniovi.eii.ds.macros.*;
public class Main {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	// Represents the document of the editor.
	StringBuilder text = new StringBuilder();

	// commands
	HashMap<String, Command> commands = new HashMap<String, Command>();

	// macros
	MacroHandler mh = new MacroHandler();
	

    public static void main(String[] args) {
        new Main().run();
    }
	
	// Main program loop.
    public void run() {
		drawLogo();
		showHelp();

		registerCommands();

		while (true) {
			UserCommand command = promptUser();
			String[] args = command.args;

			if (command.name.equals("help")){
				showHelp();
				continue;
			}

			Command cmd = commands.get(command.name);
			if (cmd != null) {
				cmd.setText(text);
				cmd.execute(args);
				text = cmd.getText();
			} else {
				System.out.println("Unknown command");
				continue;
			}

			if (mh.isRecording() && cmd.isRecordable()) {
				mh.recordCommand(cmd, args);
			}

			System.out.println(text);
		}
	}

	private void registerCommands() {
		commands.put("insert", new Insert(text));
		commands.put("delete", new Delete(text));
		commands.put("replace", new Replace(text));
		commands.put("record", new Record(mh));
		commands.put("stop", new Stop(mh));
		commands.put("execute", new Execute(mh, text));
		commands.put("open", new Open());
	}


	//$-- Some individual user commands that do a bit more work ---------------

	// private void replace(String[] args) {
	// 	if (!checkArguments(args, 2, "replace <find> <replace>"))
	// 		return;
	// 	String find = args[0];
	// 	String replace = args[1];
	// 	text = new StringBuilder(text.toString().replace(find, replace));
	// }

	//$-- Auxiliary methods ---------------------------------------------------

	// YOU DON'T NEED TO UNDERSTAND OR MODIFY THE CODE BELOW THIS LINE

	private record UserCommand(String name, String[] args) {}

    // Prompts the user and reads a line of input and returns it as a record with
	// the command and its arguments. If EOF is reached (i.e., there are nothing to
	// read), an error occurs or the user types "exit", the program exits. If there
	// are no arguments, the args array is empty.
	//
	// Example:
	//
	//   > insert "no quiero acordarme" --> returns UserInput("insert", ["no", "quiero", "acordarme"])
	//	 > delete                       --> returns UserInput("delete", [])
	//
	private UserCommand promptUser() {
		while (true) {
            System.out.print("> ");
            try {
                String line = in.readLine();
				if (line == null) System.exit(0);
				if (line.equals("exit")) exit();
				if (line.isBlank()) continue;
				String[] parts = line.split("\\s+");
				return new UserCommand(parts[0], Arrays.copyOfRange(parts, 1, parts.length));
            } catch (IOException e) {
                System.out.println("Error reading input");
				System.exit(2);
			}
		}
    }

    private boolean checkArguments(String[] args, int expected, String syntax) {
        if (args.length != expected) {
            System.out.println("Invalid number of arguments => " + syntax);
            return false;
        }
        return true;
    }

	private void exit() {
		System.out.println("Goodbye!");
		System.exit(0);
	}	

	private void drawLogo() {
		System.out.println(LOGO);
	}

	private void showHelp() {
		System.out.println(HELP);
	}

	private static final String LOGO = """

			███╗   ███╗ █████╗  ██████╗████████╗███████╗██╗  ██╗
			████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝╚██╗██╔╝
			██╔████╔██║███████║██║        ██║   █████╗   ╚███╔╝ 
			██║╚██╔╝██║██╔══██║██║        ██║   ██╔══╝   ██╔██╗ 
			██║ ╚═╝ ██║██║  ██║╚██████╗   ██║   ███████╗██╔╝ ██╗
			╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝
			""";

	private static final String HELP = """
			┌──────────────────────┬─────────────────────────────────────────────┐
			│ open <file>          │                                             │
			│ insert <text>        │ append text to the end                      │
			│ delete               │ delete the last word                        │
			│ replace <a> <b>      │ replace <a> with <b> in the whole document  │
			├──────────────────────┼─────────────────────────────────────────────┤
			│ record <macro>       │ start recording a macro                     │
			│ stop                 │ stop recording                              │
			│ execute <macro>      │ execute the specified macro                 │
			├──────────────────────┼─────────────────────────────────────────────┤
			│ help                 │                                             │
			│ exit                 │                                             │
			└──────────────────────┴─────────────────────────────────────────────┘
			""";
}
