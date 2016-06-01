package facade;

public class History implements Command {
	
	private static final String name = "history";
	private CommandHistory commandHistory = CommandHistory.getInstance();
	@Override
	public void execute() {
		int i = 1;
		for(Command cmd:commandHistory.getHistory()){
			System.out.println(i+") "+cmd.getName());
			i++;
		}

	}

	@Override
	public String getName() {
		return History.name;
	}

}
