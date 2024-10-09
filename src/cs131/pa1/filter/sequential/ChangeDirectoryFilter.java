package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class ChangeDirectoryFilter extends SequentialFilter {
	private String dir, command, newDir;
	private File file;
	
	/**
	 * Initializes the change directory filter and sets variables needed
	 * - cd command entered wrong, passes appropriate error to output 
	 * - no parameter (directory) provided by user, passes appropriate error to output 
	 * - checks for special directories ("." and "..") and handles variables for when it isn't
	 */
	public ChangeDirectoryFilter(String command) {
		this.command = command.trim();
		this.output = new LinkedList<String>();
		this.input = new LinkedList<String>();
		this.dir = System.getProperty("user.dir");
		this.newDir = this.command.substring(this.command.indexOf(" ") + 1);
		if (this.newDir.startsWith(FILE_SEPARATOR)) {
			this.newDir = this.newDir.substring(1);
		}
		if (!this.newDir.equals("..") && !this.newDir.equals(".") ) {
			this.newDir = this.dir + FILE_SEPARATOR + this.newDir;	
		}
		if (this.command.length() > 2 && this.command.indexOf(" ") == -1) {
			this.output.add(Message.COMMAND_NOT_FOUND.with_parameter(this.command).toString().trim());
		} 
		if (this.command.equals("cd")) {
			this.output.add(Message.REQUIRES_PARAMETER.with_parameter(this.command).toString().trim());
		}
	}
	
	/** checks for a subcommand/something being passed before it
	 * - can't have input, passes appropriate message if there is one
	 * - also can't have following subcommand/output, passes appropriate message if there is one
	 * - adds appropriate lines to the output 
	 * - "." handled, sets dir to current one (nothing changes in this)
	 * - ".." handled, finds last index of file sep. and sets dir to appropriate substring (goes back one dir)
	 * - not mentioned errors or special cases, tries to set dir to the one passed by user 
	 * - if given directory doesn't exit, passes appropriate error message to output/user
	 */
	public void process() {
		if (!this.input.isEmpty()) {
			this.output.add(Message.CANNOT_HAVE_INPUT.with_parameter(this.command).toString().trim());
		} else if( this.next != null) {
			this.output.add(Message.CANNOT_HAVE_OUTPUT.with_parameter(this.command).toString().trim());
		} else {
			if (this.newDir.equals("..")) {
				int parInd = this.dir.lastIndexOf(FILE_SEPARATOR);
				this.newDir = this.dir.substring(0, parInd);
				this.file = new File(this.newDir);
				System.setProperty("user.dir", this.newDir);
			} else if(this.newDir.equals(".")) {
				this.newDir = this.dir;
			} else {
				this.file = new File(this.newDir);
				if (this.file.isDirectory()) {
					System.setProperty("user.dir", this.file.getAbsolutePath());
				} else {
					if (this.command.length() > 2 && this.command.contains(" ")) {
						this.output.add(Message.DIRECTORY_NOT_FOUND.with_parameter(this.command).toString().trim());						
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
