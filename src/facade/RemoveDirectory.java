package facade;

import java.util.ArrayList;
import java.util.Iterator;

public class RemoveDirectory implements Command {

	private static final String name = "rmdir";
	private FileSystem fileSystem = FileSystem.getfileSystem();
	private String dirName;
	
	
	public RemoveDirectory(String dirName) {
		this.dirName = dirName;
	}

	@Override
	public void execute() {
		
		if(dirName!=null){
			Directory currentDirectory = fileSystem.getCurrent();
			ArrayList<FSElement> children = currentDirectory.getChildren();
			boolean foundDir = false;
			Iterator<FSElement> i = children.iterator();
			while(i.hasNext()){
				FSElement child = i.next();
				if (child.getName().equals(dirName) && child.getType().equals("Directory")) {
					i.remove();
					foundDir = true;
					System.out.println("successfully removed the directory "+ dirName +"from current directory "+ fileSystem.getCurrent().getName());
					}
			}
			
			
			if (!foundDir) {		
				System.out.println("directory " + dirName + "doesnt exist in " + fileSystem.getCurrent().getName());
			}
		}

	}

	@Override
	public String getName() {
		return RemoveDirectory.name;
	}

}
