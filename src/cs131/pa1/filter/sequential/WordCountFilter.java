package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class WordCountFilter extends SequentialFilter {
	private int lines, words, chars;
	private String command;

	/**
	 * Initializes the word count filter and sets variables needed
	 */
	public WordCountFilter(String command) {
		this.command = command.trim();
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.lines = 0;
		this.words = 0;
		this.chars = 0;
	}

	/**
	 * checks for a subcommand/something being passed before it
	 * - requires input, passes appropriate message if there isn't one
	 * - filters out specific lines that shouldn't be counted
	 * - processes the lines that should be accounted for
	 * - prints when output is empty
	 */
	public void process() {
		
		if (this.prev == null) {
				this.output.add(Message.REQUIRES_INPUT.with_parameter(this.command).toString().trim());
			
		} else {
			while (!input.isEmpty() ){
				String line = input.poll();
				if (line.startsWith("The command") || line.startsWith("The file") || line.startsWith("The directory")) {
					this.output.add(line);
				} else {
					processLine(line);					
				}
			}
			if (this.output.size() == 0) { 
				this.output.add(this.lines + " " + this.words + " " + this.chars);												
			}
		}
	}


	@Override
	/** 
	 * counts lines, words, and characters from lines that should be counted
	 * - line variable increased by 1 per line passed 
	 * - gets length of line and increases character count by that value 
	 * - line is split based on the spaces, each word being added into an array
	 * - increases word variable based on array length
	 */
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		this.lines++;
		this.chars += line.length();
		String[] wordArr = line.split(" ");
		if (wordArr.length > 0 && !wordArr[0].equals("")) { 	
			this.words += wordArr.length;
		}
		return null;

	}

}
