package facade;

import java.util.ArrayList;

public class PrintDirectoryOrFile implements Command {

	private static final String name = "dir";
	private FileSystem fileSystem = FileSystem.getfileSystem();
	private String arg1;
	private String arg2;

	public PrintDirectoryOrFile(String arg1, String arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	@Override
	public void execute() {
		if (arg1 == null && arg2 == null) {
			Directory currentDirectory = fileSystem.getCurrent();
			if (currentDirectory != null) {
				fileSystem.showAllElements(currentDirectory);
			}
		} else if (arg1 != null && arg2 == null && !arg1.equals("..")) {

			ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
			boolean foundFile = false;
			boolean foundDir = false;
			for (FSElement child : children) {
				if (child.getName().equals(arg1) && child.getType().equals("File")) {
					fileSystem.showElement(child);
					foundFile = true;

				}else if (child.getName().equals(arg1) && child.getType().equals("Directory")) {
					fileSystem.setCurrent((Directory) child);
					foundDir = true;
					fileSystem.showAllElements((Directory) child);
				}
			}
			if (!foundFile&&!foundDir) {
				System.out.println("file/directory with name " + arg2 + "doesnot exist in " + fileSystem.getCurrent().getName());
			}

		}else if (arg1 != null && arg2 == null && arg1.equals("..")) {

			Directory currentDirectory = fileSystem.getCurrent();
			if (currentDirectory != null) {
				Directory parent = currentDirectory.getParent();
				if(parent!=null){
					fileSystem.setCurrent(parent);
					fileSystem.showAllElements(parent);	
				}else{
					Directory root = fileSystem.getRoot();
					fileSystem.setCurrent(root);
					fileSystem.showAllElements(root);
				}	
			}

		} else if(arg1 != null && arg2 != null){
			ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
			boolean foundDir = false;
			for (FSElement child : children) {
				if (child.getName().equals(arg1) && child.getType().equals("Directory")) {
					fileSystem.setCurrent((Directory) child);
					foundDir = true;
					ArrayList<FSElement> children1 = ((Directory) child).getChildren();
					boolean foundFile = false;
					for (FSElement child1 : children1) {
						if (child1.getName().equals(arg2) && child1.getType().equals("File")) {
							fileSystem.showElement(child1);
							foundFile = true;

						}
					}
					if (!foundFile) {
						System.out.println("file " + arg2 + "doesnot exist in " + fileSystem.getCurrent().getName());
					}
				}
			}
			if (!foundDir) {
				System.out.println("directory " + arg1 + "doesnot exist in " + fileSystem.getCurrent().getName());
			}
		}


	}

	@Override
	public String getName() {
		return PrintDirectoryOrFile.name;
	}

}
