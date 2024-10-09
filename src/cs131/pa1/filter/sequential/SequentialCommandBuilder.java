package cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the parsing and execution of a command. It splits the raw
 * input into separated subcommands, creates subcommand filters, and links them
 * into a list.
 * 
 * @author cs131a
 *
 */
public class SequentialCommandBuilder {
	/**
	 * Creates and returns a list of filters from the specified command
	 * 
	 * @param command the command to create filters from
	 * @return the list of SequentialFilter that represent the specified command
	 */
	public static List<SequentialFilter> createFiltersFromCommand(String command) {
		command = command.trim();
		List<SequentialFilter> cmdList = new ArrayList<SequentialFilter>();
		int pipeInd = command.indexOf("|");
		while (pipeInd != -1) {
			String subCmd = command.substring(0, pipeInd);
			command = command.substring(pipeInd+1).trim();
			int redirInd = subCmd.indexOf(">");
			if (redirInd == -1) {
				SequentialFilter filt = constructFilterFromSubCommand(subCmd);
				cmdList.add(filt);
			} else {
				String subCmd1 = subCmd.substring(0, redirInd);
				SequentialFilter filt1 = constructFilterFromSubCommand(subCmd1);
				cmdList.add(filt1);				
				String subCmd2 = subCmd.substring(redirInd);
				SequentialFilter filt2 = constructFilterFromSubCommand(subCmd2);
				cmdList.add(filt2);
			}
			pipeInd = command.indexOf("|");
		}
		int dirInd = command.indexOf(">");
		if (dirInd > 0) {
			String subCmd = command.substring(0, dirInd);
			SequentialFilter penultFilt = constructFilterFromSubCommand(subCmd);
			cmdList.add(penultFilt);
			command = command.substring(dirInd);
		}
		SequentialFilter finalFilt = constructFilterFromSubCommand(command); 
		cmdList.add(finalFilt);
		linkFilters(cmdList);
		return cmdList;

	}

	/**
	 * Returns the filter that appears last in the specified command
	 * 
	 * @param command the command to search from
	 * @return the SequentialFilter that appears last in the specified command
	 */
	private static SequentialFilter determineFinalFilter(String command) { 
		return null;
	}


	/**
	 * Returns a string that contains the specified command without the final filter
	 * 
	 * @param command the command to parse and remove the final filter from
	 * @return the adjusted command that does not contain the final filter
	 */
	private static String adjustCommandToRemoveFinalFilter(String command) {
		return null;
	}

	/**
	 * Creates a single filter from the specified subCommand
	 * 
	 * @param subCommand the command to create a filter from
	 * @return the SequentialFilter created from the given subCommand
	 */
	private static SequentialFilter constructFilterFromSubCommand(String subCommand) {
		if (subCommand.contains("pwd")) {
			return new PrintDirFilter(subCommand);
		} else if(subCommand.contains("ls")) {
			return new ListFilter(subCommand);
		} else if(subCommand.contains("cd")) {
			return new ChangeDirectoryFilter(subCommand);
		} else if(subCommand.contains("cat")) {
			return new CatFilter(subCommand);
		} else if(subCommand.contains("head")) {
			return new HeadFilter(subCommand);				
		} else if(subCommand.contains("tail")) {
			return new TailFilter(subCommand);				
		} else if(subCommand.contains("grep")) {
			return new GrepFilter(subCommand);
		} else if(subCommand.contains("wc")) {
			return new WordCountFilter(subCommand);
		} else if(subCommand.contains("uniq")) {
			return new UniqFilter(subCommand);
		} else if(subCommand.contains(">")) {
			return new RedirectFilter(subCommand);
		} else {
			return new ErrorFilter(subCommand);
		}



	}

	/**
	 * links the given filters with the order they appear in the list
	 * 
	 * @param filters the given filters to link
	 * @return true if the link was successful, false if there were errors
	 *         encountered. Any error should be displayed by using the Message enum.
	 */
	private static boolean linkFilters(List<SequentialFilter> filters) {
		for (int i = 0; i < filters.size()-1; i ++) {
			SequentialFilter currFilt = filters.get(i);
			SequentialFilter nextFilt = filters.get(i+1);
			if (i > 0) {
				SequentialFilter prevFilt = filters.get(i-1);
				currFilt.setPrevFilter(prevFilt);
			}
			currFilt.setNextFilter(nextFilt);
			filters.set(i, currFilt);

		}
		return true;
	}
}
