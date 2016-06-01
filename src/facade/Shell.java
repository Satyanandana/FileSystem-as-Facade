package facade;

import java.io.IOException;
import java.util.Scanner;

public class Shell {
	private CommandHistory commandHistory = CommandHistory.getInstance();
	private final static Scanner scanner = new Scanner(System.in);
	private boolean run = true;
	private String command ;
	
	
	public Shell() {
		
	}

	public void showCommandList(){
		System.out.println("------------------------------------ List of Shell Comands ---------------------------------");
		System.out.println("");
		System.out.println("– pwd                    • Print the current working directory.");
		System.out.println("– cd <dir name>          • Change the current directory to the specified directory.");
		System.out.println("– cd                     • Change the current directory to the root directory.");
		System.out.println("– ls                     • Print the name of every file, directory and link in the current directory.");
		System.out.println("– dir                    • Print the information (i.e., kind, name, size and owner) of every file, directory and link in the current directory.");
		System.out.println("– dir <dir/file name>    • Print the specified directory’s/file’s information. Accept relative (not absolute) directory name. Accept “..”");
		System.out.println("– mkdir <dir name>       • Make the specified directory in the current directory.");
		System.out.println("– rmdir <dir name>       • Remove the specified directory in the current directory.");
		System.out.println("– ln <target (real) dir/file> <link (alias) dir/file> • Make a link");
		System.out.println("– history                • Print a sequence of previously-executed commands.");
		System.out.println("– redo                   • Redo the most recently-executed command.");
		System.out.println("– sort  <alpha/revalpha> • Sort directories and files in the current directory");
		System.out.println("– chown                  • Change the owner of a file/directory accepts “.” ");
		System.out.println("");
		System.out.println("---------------------------------------------------------------------------------------------");
	}
	
	public void run() throws IOException{
		
		while(run){
			System.out.print(">");
			command = scanner.nextLine();
			String[] commandArgs = command.split(" ");
			String cmd = commandArgs[0];
			if(cmd!=null){
				triggerCommand(cmd,commandArgs);
			}
		}
		
		
	}
	
	public void triggerCommand(String cmd,String[] commandArgs) {
		
		switch (cmd) {

		case "pwd":
			Command pwd = new PrintCurrentWorkingDirectory();
			pwd.execute();
            commandHistory.push(pwd);
			break;

		case "cd":
			String directory = null;
			if(commandArgs.length==2){
				directory = commandArgs[1];
			}
			Command cd = new ChangeDirectory(directory);
			cd.execute();
			commandHistory.push(cd);
			break;

		case "ls":
            Command ls = new Ls();
            ls.execute();
			commandHistory.push(ls);
			break;

		case "dir":
			String[] args = new String[2];
			if(commandArgs.length==2){
				if(commandArgs[1].contains("/")){
					String[] parts = commandArgs[1].split("/");
					args[0] = parts[0];
					args[1] = parts[1];
				}else {
					args[0] = commandArgs[1];
					args[1] = null;
				}
			 
			}else{
				args[0] = null;
				args[1] = null;
			}
			Command dir = new PrintDirectoryOrFile(args[0], args[1]);
			dir.execute();
			commandHistory.push(dir);
			break;

		case "mkdir":
			String directory1 = null;
			if(commandArgs.length==2){
				directory1 = commandArgs[1];
				Command mkdir = new MakeDirectory(directory1);
				mkdir.execute();
				commandHistory.push(mkdir);
			}else{
				System.out.println("please enter the directory name");
			}
			
			break;

		case "rmdir":
			String directory2 = null;
			if(commandArgs.length==2){
				directory2 = commandArgs[1];
				Command rmdir = new RemoveDirectory(directory2);
				rmdir.execute();
				commandHistory.push(rmdir);
			}else{
				System.out.println("please enter the directory name");
			}
			break;

		case "ln":
			if(commandArgs.length==3){
				Command ln = new MakeLink(commandArgs[1], commandArgs[2]);
				ln.execute();
				commandHistory.push(ln);
			}else{
				System.out.println("Please dont forget to enter target and alias");
			}
			break;

		case "history":
			Command history = new History();
			history.execute();
			commandHistory.push(history);
			break;

		case "redo":
			Command redo = new Redo();
			redo.execute();
			break;

		case "sort":
			Command sort;
			if(commandArgs.length==2){
			sort = new Sort(commandArgs[1]);
			}else{
				sort = new Sort(null);	
			}
			sort.execute();
			commandHistory.push(sort);
			break;

		case "chown":
			String[] args1 = new String[3];
			if(commandArgs.length==3){
				if(commandArgs[1].contains("/")){
					String[] parts = commandArgs[1].split("/");
					args1[0] = parts[0];
					args1[1] = parts[1];
					
				}else {
					args1[0] = commandArgs[1];
					args1[1] = null;
				}
				args1[2] = commandArgs[2];
				Command chown = new ChangeOwner(args1[0], args1[1], args1[2]);
				chown.execute();
				commandHistory.push(chown);
			 
			}else{
				System.out.println("Please enter file/directory and owner name");
			}
			break;

		case "help":
			showCommandList();
			break;

		case "exit":
			run = false;
			break;

		default:
			System.out.println("Please enter a valid command");
		}
	}

}
