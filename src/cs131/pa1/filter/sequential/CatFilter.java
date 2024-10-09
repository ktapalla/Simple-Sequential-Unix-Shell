package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class CatFilter extends SequentialFilter {
	private String dir, command, newDir;
	private File file;
	
	/**
	 * Initializes the cat filter and sets variables needed
	 */
	public CatFilter(String command) {
		this.command = command.trim();
		this.input = new LinkedList<String>();
		this.output = new LinkedList<String>();
		this.dir = System.getProperty("user.dir");
			
	}
	
	/**
	 * checks for a subcommand/something being passed before it
	 * - can't have input, passes appropriate message if there is one
	 * - gets index of the first space 
	 * - checks for spelling/typing of command, passes appropriate message if wrong 
	 * - checks if there is a paramenter/query passed, passes appropriate message one isn't provided 
	 * - gets the appropriate directory of the file using space index, removes file sep. if typed in by user
	 * - checks if file exists, passes appropriate error message if it doesn't 
	 * - no other issues possible, opens and reads file line by line, adding each line to output
	 */
	public void process() {
		if (this.input.size() > 0) {
			this.input.clear();
			this.output.add(Message.CANNOT_HAVE_INPUT.with_parameter(this.command).toString().trim());
		} else {
			try {
				int nInd = this.command.indexOf(" ");
				if (nInd == -1) {
					this.input.clear();
					if (this.command.length() > 3) {
						this.output.add(Message.COMMAND_NOT_FOUND.with_parameter(this.command).toString().trim());
					} else {
						this.output.add(Message.REQUIRES_PARAMETER.with_parameter(this.command).toString().trim());				
					}
				} else {
					this.newDir = this.command.substring(nInd+1);
					if (this.newDir.startsWith(FILE_SEPARATOR)) {
						this.newDir = this.newDir.substring(1);
					}
					this.newDir = this.dir + FILE_SEPARATOR + this.newDir;
					this.file = new File(this.newDir);
					if (!this.file.isFile() ) {
						this.input.clear();
						this.output.add(Message.FILE_NOT_FOUND.with_parameter(this.command).toString().trim());					
					} else {
						Scanner s = new Scanner(this.file);
						while (s.hasNextLine()) {
							this.output.add(s.nextLine());
						}
						s.close();					
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
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
