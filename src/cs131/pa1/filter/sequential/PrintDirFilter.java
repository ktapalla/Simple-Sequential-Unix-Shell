package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class PrintDirFilter extends SequentialFilter {
	private String dir, command;
	
	/**
	 * Initializes the print working directory filter and sets variables needed
	 */
	public PrintDirFilter(String command) {
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.dir = System.getProperty("user.dir");
		this.command = command.trim();
	}
	
	/**
	 * checks for a subcommand/something being passed before it
	 * - can't have input, passes appropriate message if there is one
	 * - otherwise adds user's current directory to the output
	 */
	public void process() {
		if (this.input.size() > 0) {
			this.output.add(Message.CANNOT_HAVE_INPUT.with_parameter(this.command).toString().trim());
		} else {
			this.output.add(this.dir);			
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
