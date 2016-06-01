package facade;

import java.util.ArrayList;

public class CommandHistory {
	private static CommandHistory commandHistory = null;
	private ArrayList<Command> history = new ArrayList<>();
	
	private CommandHistory(){
		
	}
	
	public static CommandHistory getInstance(){
		
		if(commandHistory == null){
			commandHistory = new CommandHistory();
		}
		
		return commandHistory;
		
	}
	
	public void push(Command command){
		this.history.add(command);
	}
	
	public Command pop(){
		Command previous = this.history.get(this.history.size()-1);
		return previous;
	}

	public ArrayList<Command> getHistory() {
		return history;
	}

	
}
