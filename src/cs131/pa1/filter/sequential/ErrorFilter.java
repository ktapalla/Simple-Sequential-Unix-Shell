package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class ErrorFilter extends SequentialFilter {
	
	private String command;
	
	/**
	 * Initializes the error filter and sets variables needed
	 */
	public ErrorFilter(String command) {
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.command = command.trim();
	}
	
	/**
	 * this is used when the command being run by the user doesn't exist in the program
	 */
	public void process() {
		this.output.add(Message.COMMAND_NOT_FOUND.with_parameter(this.command).toString().trim());
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
