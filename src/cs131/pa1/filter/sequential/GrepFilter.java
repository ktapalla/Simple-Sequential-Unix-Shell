package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class GrepFilter extends SequentialFilter {
	private String incStr, command;
	
	/**
	 * Initializes the grep filter and sets variables needed
	 */
	public GrepFilter(String command) {
		this.command = command.trim();
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.incStr = "";
	}
	
	/**
	 * checks for a subcommand/something being passed before it
	 * - requires input, passes appropriate message if there isn't one
	 * - passes appropriate lines to be processed to another method 
	 * - adds appropriate lines to the output 
	 */
	public void process() {
		if (this.prev == null) {
			this.output.add(Message.REQUIRES_INPUT.with_parameter(this.command).toString().trim());
		} else {
			while (!input.isEmpty()){
				String line = input.poll();
				String processedLine = processLine(line);
				if (processedLine != null){
					output.add(processedLine);
				}
			}	
		}
	}
	
	@Override
	/** 
	 * checks for all lines from input that contain the query 
	 * - gets index of the first space 
	 * - checks for spelling/typing of command, passes appropriate message if wrong 
	 * - checks if there is a paramenter/query passed, passes appropriate message one isn't provided 
	 * - no issues found, uses first index of a space found to specify the query/parameter string 
	 * - if the query is found in the line, it is returned
	 */
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		int pInd = this.command.indexOf(" ");
		if (pInd == -1) {
			this.input.clear();
			if (this.command.length() > 4) {
				this.output.add(Message.COMMAND_NOT_FOUND.with_parameter(this.command).toString().trim());
			} else {
				this.output.add(Message.REQUIRES_PARAMETER.with_parameter(this.command).toString().trim());				
			}
		} else {
			this.incStr = this.command.substring(pInd+1);
			if (line.contains(this.incStr)) {
				return line;
			}
		}
		return null;
	}

}
