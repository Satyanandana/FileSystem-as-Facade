package facade;

import java.util.ArrayList;

public class ChangeOwner implements Command {

	private static final String name = "dir";
	private FileSystem fileSystem = FileSystem.getfileSystem();
	private String arg1;
	private String arg2;
	private String owner;

	public ChangeOwner(String arg1, String arg2,String owner) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.owner = owner;
	}

	@Override
	public void execute() {
		if (arg1!=null && arg1.equals(".")) {
			Directory currentDirectory = fileSystem.getCurrent();
			if (currentDirectory != null) {
				currentDirectory.setOwner(owner);
				System.out.println("Successfully changed the owner of "+ currentDirectory.getName());
			}
		}else if (arg1 != null && arg2 == null) {

			ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
			boolean foundFile = false;
			boolean foundDir = false;
			for (FSElement child : children) {
				if (child.getName().equals(arg1) && child.getType().equals("File")) {
					child.setOwner(owner);
					foundFile = true;
					System.out.println("Successfully changed the owner of "+ child.getName());

				}else if (child.getName().equals(arg1) && child.getType().equals("Directory")) {
					child.setOwner(owner);
					foundDir = true;
					System.out.println("Successfully changed the owner of "+ child.getName());
				}
			}
			if (!foundFile&&!foundDir) {
				System.out.println("file/directory with name " + arg2 + "doesnot exist in " + fileSystem.getCurrent().getName());
			}

		} else if(arg1 != null && arg2 != null){
			ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
			boolean foundDir = false;
			for (FSElement child : children) {
				if (child.getName().equals(arg1) && child.getType().equals("Directory")) {
					
					foundDir = true;
					ArrayList<FSElement> children1 = ((Directory) child).getChildren();
					boolean foundFile = false;
					for (FSElement child1 : children1) {
						if (child1.getName().equals(arg2) && child1.getType().equals("File")) {
							child1.setOwner(owner);
							foundFile = true;
							System.out.println("Successfully changed the owner of "+ child1.getName());
						}
					}
					if (!foundFile) {
						System.out.println("file " + arg2 + " doesnot exist in " + child.getName());
					}
				}
			}
			if (!foundDir) {
				System.out.println("directory " + arg1 + " doesnot exist in " + fileSystem.getCurrent().getName());
			}
		}

	}

	@Override
	public String getName() {
		return ChangeOwner.name;
	}

}
