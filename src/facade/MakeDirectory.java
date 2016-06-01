package facade;

import java.util.ArrayList;

public class MakeDirectory implements Command {

	private static final String name = "mkdir";
	private FileSystem fileSystem = FileSystem.getfileSystem();
	private String dirName;
		
	public MakeDirectory(String dirName) {
		this.dirName = dirName;
	}

	@Override
	public void execute() {
		if(dirName!=null){
		Directory currentDirectory = fileSystem.getCurrent();
		ArrayList<FSElement> children = currentDirectory.getChildren();
		boolean foundDir = false;
		for (FSElement child : children) {
			if (child.getName().equals(dirName) && child.getType().equals("Directory")) {
				foundDir = true;
				}
		}
		if (foundDir) {
			System.out.println("directory " + dirName + "already exists in " + fileSystem.getCurrent().getName());
		}else{
			Directory dir = new Directory(currentDirectory, dirName, currentDirectory.getOwner());
			System.out.println("successfully created a new directory "+ dirName +"in current directory "+ fileSystem.getCurrent().getName());
		}
	}

	}

	@Override
	public String getName() {
		return MakeDirectory.name;
	}

}
