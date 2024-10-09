package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class HeadFilter extends SequentialFilter {
	private int read;
	private String command;

	/**
	 * Initializes the head filter and sets variables needed
	 */
	public HeadFilter(String command) {
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.read = 0;
		this.command = command.trim();
	}

	@Override
	/**
	 * checks for a subcommand/something being passed before it
	 * - requires input, passes appropriate message if there isn't one
	 * - keeps track of how many lines have been read 
	 * - adds first 10 (or less if there's less than 10) lines to output 
	 */
	public void process() {
		if (this.prev == null) {
			this.output.add(Message.REQUIRES_INPUT.with_parameter(this.command).toString().trim());
		}
		while (this.read < 10) {
			String str = this.input.poll();
			if (str != null) {
				this.output.add(str);
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
