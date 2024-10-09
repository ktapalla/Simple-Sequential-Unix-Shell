package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class TailFilter extends SequentialFilter {

	private int read, start;
	private String command;
	
	/**
	 * Initializes the tail filter and sets variables needed
	 */
	public TailFilter(String command) {
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.start = 0;
		this.read = 0;
		this.command = command.trim();
	}
	
	@Override
	/**
	 * checks for a subcommand/something being passed before it
	 * - requires input, passes appropriate message if there isn't one
	 * - gets the start and end index of last 10 lines 
	 * - keeps track of how many lines/indexes have been read 
	 * - adds lines between start and end indexes to output (those are the last 10)
	 */
	public void process() {
		if (this.prev == null) {
			this.output.add(Message.REQUIRES_INPUT.with_parameter(this.command).toString().trim());
		}
		this.start = this.input.size() - 10;
		int lastInd = this.input.size();
		while (!this.input.isEmpty()) {
			String str = this.input.poll();
			if (str != null && this.read < lastInd && this.read >=this.start) {
				this.output.add(str);
				this.start++;
			}
			this.read++;
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
