package facade;

import java.util.ArrayList;

public class Ls implements Command {
	
	private static final String name = "ls";
	private FileSystem fileSystem = FileSystem.getfileSystem();
    

	@Override
	public void execute() {
		ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
		for(FSElement child : children){
			System.out.println(child.getType()+" : "+child.getName());
		}

	}

	@Override
	public String getName() {
		return Ls.name;
	}

}
