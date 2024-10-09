package cs131.pa1.filter.sequential;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import cs131.pa1.filter.Message;

public class UniqFilter extends SequentialFilter {
	private Set<String> prevLines;
	private String command;

	/**
	 * Initializes the unique filter and sets variables needed
	 */
	public UniqFilter(String command) {
		this.command = command .trim();
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.prevLines = new HashSet<String>();
	}

	/**
	 * checks for a subcommand/something being passed before it
	 * - requires an input, passes appropriate message if there isn't one
	 * - processes the lines one by one 
	 * - adds the unique lines to the output
	 */
	public void process() {
		if (!this.input.isEmpty()) {
			while (!input.isEmpty()){
				String line = input.poll();
				String processedLine = processLine(line);
				if (processedLine != null){
					output.add(processedLine);
				}
			}		
		} else {
			this.output.add(Message.REQUIRES_INPUT.with_parameter(this.command).toString().trim());
		}
	}

	@Override
	/** 
	 * checks for the unique lines 
	 * - compares line being passed to the set of previous lines saved 
	 * - returns null if the line is already in there  
	 * - adds line to set if not to store for future comparisons and returns line 
	 */
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		if (this.prevLines.contains(line)) {
			return null;
		} else {
			this.prevLines.add(line);
			return line;
		}
	}

}
