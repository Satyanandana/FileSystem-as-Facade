package facade;

public class PrintCurrentWorkingDirectory implements Command {

	private static final String name = "pwd";
	private FileSystem fileSystem = FileSystem.getfileSystem();
	
	@Override
	public void execute() {
		Directory currentDirectory = fileSystem.getCurrent();
			System.out.println("The current working directory is "+currentDirectory.getName());
	}

	@Override
	public String getName() {
		
		return PrintCurrentWorkingDirectory.name;
	}

}
