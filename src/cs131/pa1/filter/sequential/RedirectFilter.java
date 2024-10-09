package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class RedirectFilter extends SequentialFilter {
	private String dir, command;
	private File file;
	private PrintStream writer;

	/**
	 * Initializes the redirect filter and sets variables needed
	 * - immediately checks for following filter (not allowed) 
	 * - passes appropriate message if there is one
	 */
	public RedirectFilter(String command) {
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.dir = System.getProperty("user.dir");
		this.command = command.trim();
		if (this.next != null) {
			this.output.add(Message.CANNOT_HAVE_OUTPUT.with_parameter(this.command));
		}
	}

	/**
	 * checks for a subcommand/something being passed before it
	 * - requires input, passes appropriate message if there isn't one
	 * - also can't have following subcommand/output, passes appropriate message if there is one
	 * - appends given name for new file to current directory and creates the file 
	 * - writes necessary text to that new file, saves it, and closes it
	 */
	public void process() {
		if (this.prev == null || this.input.isEmpty()) {
			this.output.add(Message.REQUIRES_INPUT.with_parameter(this.command).toString().trim());
		} else if (this.next != null) {
			this.output.add(Message.CANNOT_HAVE_OUTPUT.with_parameter(this.command).toString().trim());
		} else {
			int nInd = this.command.indexOf(" ");
			if (nInd == -1) {
				this.output.add(Message.REQUIRES_PARAMETER.with_parameter(this.command).toString().trim());			
			} else {
				String fileName = this.dir + FILE_SEPARATOR + this.command.substring(nInd+1);
				this.file = new File(fileName);
				try {
					this.writer = new PrintStream(this.file);
					while (!this.input.isEmpty()) {
						String write = this.input.poll();
						if (this.writer != null) {
							this.writer.println(write);				
						}
					}
					this.writer.close();			

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
