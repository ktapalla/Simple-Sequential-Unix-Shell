package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class ListFilter extends SequentialFilter {

	private String dir, command;

	/**
	 * Initializes the list filter and sets variables needed
	 */
	public ListFilter(String command) {
		this.command = command.trim();
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.dir = System.getProperty("user.dir");
	}

	@Override
	/**
	 * checks for a subcommand/something being passed before it
	 * - can't have input, passes appropriate message if there is one
	 * - creates an array with files in current directory user is in 
	 * - goes through the array and gets/adds file names to output  
	 */
	public void process() {
		if (this.input.size() > 0) {
			this.output.add(Message.CANNOT_HAVE_INPUT.with_parameter(this.command).toString().trim());
		} else {
			File cwd = new File(this.dir);
			if (cwd.isDirectory()) {
				File[] files = cwd.listFiles();
				if (files != null) {
					for (File f : files) {
						this.output.add(f.getName());						
					}							
				}
			}			
		}
	}

	@Override
	/**
	 * method not required/used for this filter
	 */
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
