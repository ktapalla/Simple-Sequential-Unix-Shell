# README - Simplified/Sequential UNIX Shell 

This program is a simplified version of a UNIX shell, which is a command line interpreter. It allows users to interact with a computer's operating system through a set of specific commands. This program was made for an Operating Systems class, and files we were told not to edit have not had any code or comments changed from the original files provided. 

## Installation and Execution 

Get the files from GitHub through your shell, move into the directory you have it in. Then you'll want to go into the src folder as well. 

When you're in the src folder, compile the project with: 
``` bash
javac cs131/pa1/filter/*.java cs131/pa1/filter/sequential/*.java 
```
After compiling, start the project with:
``` bash
java cs131/pa1/filter/sequential/SequentialREPL
```

Your terminal/console should now show you a message saying:
``` bash
Welcome to the Unix-ish command line.
> 
```

This indicates that the program is up and running, and that you can use it to interact with your computer's operating system. 

## Accepted Commands 

|  Command  |  Description  |
|:----------|:--------------|
| ``` pwd ``` |  Returns current working directory  |
| ``` ls ``` |  Lists the files in the current working directory  |
| ``` cd <dir> ``` |  Changes the current working directory to ``` <dir> ``` |
| ``` cat <file> ``` |  Writes ``` <file>```'s contents into pipe or stdout  |
| ``` head/tail ``` |  Returns up to the first/last 10 lines from piped input  |
| ``` grep <query> ``` |  Returns all lines from piped input that contain ``` <query> ``` |
| ``` wc ``` |  Counts the number of lines, words, and characters in the piped input  |
| ``` uniq ``` |  Returns all lines from piped input that are not the same as the previous line  |
| ``` > <file> ``` |  Redirects output of preceding command, writes it to file ``` <file> ```  |
| ``` exit ``` |  Prints "goodbye" and terminates the REPL  |

## Notes 

### Parameters: 

Parameters are the arguments specified in the angle brackets ``` <> ``` above that are towards the right of the subcommands. All parameters are required to successfully run those commands. If not included, and error message will be given to the user instead. 

### Comound Commands/Pipelines: 

The REPL can take pipelines, or compound commands, where the processes are chained together so that the output of each process is passed into the input of the next command. The compound commands are read from left to right, using the pipe operator **|**. For example, compound commands would be put in like this: 

``` command1 ``` **|** ``` command2 ``` **|** ``` command3 ```

For the above example, the output of command1 would be the input of command2, and the output of command2 would be the input of command3. 