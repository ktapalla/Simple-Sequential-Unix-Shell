package cs131.pa1.filter.sequential;

import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;

/**
 * The main implementation of the REPL loop (read-eval-print loop). It reads
 * commands from the user, parses them, executes them and displays the result.
 * 
 * @author cs131a
 *
 */
public class SequentialREPL {
	/**
	 * the path of the current working directory
	 */
	static String currentWorkingDirectory;

	/**
	 * The main method that will execute the REPL loop
	 * - gets/sets current user directory upon start of the program 
	 * - reads console for user input of commands to be run 
	 * - prints appropriate messages to the user after commands have been processed by the program 
	 * @param args not used
	 */
	public static void main(String[] args) {
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner consoleReader = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		String cmd = "";
		
		while(!cmd.equals("exit") && !cmd.equals("Exit") ) {
			System.out.print(Message.NEWCOMMAND);
			cmd = consoleReader.nextLine();

			if (cmd.equals("exit") || cmd.equals("Exit") ) {
				System.out.print(Message.GOODBYE);
				currentWorkingDirectory = System.setProperty("user.dir", currentWorkingDirectory);
			} else {
				List<SequentialFilter> filterList = SequentialCommandBuilder.createFiltersFromCommand(cmd);
				int listSize = filterList.size();
				for (int i = 0; i < listSize; i++) {
					SequentialFilter filter = filterList.get(i);
					filter.process();
					if (filter instanceof ErrorFilter) {
						filterList.add(filterList.size(), filter);
						i = listSize;
					}
				}

				SequentialFilter printOut = filterList.get(filterList.size()-1);
				for (String out : printOut.output) {
					System.out.println(out);
				}
			}
		}
		consoleReader.close();
	}

}
