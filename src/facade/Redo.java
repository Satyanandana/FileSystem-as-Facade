package facade;

public class Redo implements Command {
	private static final String name = "redo";
	
	
	@Override
	public void execute() {
		CommandHistory commandHistory = CommandHistory.getInstance();
		Command previousCmd = commandHistory.pop();
		previousCmd.execute();
	}

	@Override
	public String getName() {
		return Redo.name;
	}

}
