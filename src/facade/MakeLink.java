package facade;

import java.util.ArrayList;

public class MakeLink implements Command {
	private static final String name = "ln";
	private FileSystem fileSystem = FileSystem.getfileSystem();
	private String target;
	private String alias;
	
	
	
	public MakeLink(String target, String alias) {
		this.target = target;
		this.alias = alias;
	}

	@Override
	public void execute() {
		
		Directory currentDirectory = fileSystem.getCurrent();
		ArrayList<FSElement> children = currentDirectory.getChildren();
		boolean foundDir = false;
		FSElement targetElement = null;
		for (FSElement child : children) {
			if (child.getName().equals(alias) && child.getType().equals("Link")) {
				foundDir = true;
				}
			if (child.getName().equals(target)) {
				targetElement = child;
				}
		}
		if (foundDir) {
			System.out.println("link " + alias + "already exists in " + fileSystem.getCurrent().getName());
		}
		if(targetElement!=null){
			Link link = new Link(currentDirectory, targetElement, alias, currentDirectory.getOwner());
			System.out.println("Successfully created a link in "+currentDirectory.getName()+" "+currentDirectory.getType()+" for "+targetElement.getName()+" "+targetElement.getType()+" with alias "+ alias);
		}else{
			System.out.println("target not found in "+currentDirectory.getName());
		}
		
		
		
	
		

	}

	@Override
	public String getName() {
		return MakeLink.name;
	}

}
