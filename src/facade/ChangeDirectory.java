package facade;

import java.util.ArrayList;

public class ChangeDirectory implements Command {

	private static final String name = "cd";
	private FileSystem fileSystem = FileSystem.getfileSystem();
	private String changeTo = null;

	public ChangeDirectory(String changeTo) {
		this.changeTo = changeTo;
	}

	@Override
	public void execute() {
		if (changeTo != null && !changeTo.equals("..")) {
			ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
			boolean foundDir = false;
			for (FSElement child : children) {
				if (child.getName().equals(changeTo) && child.getType().equals("Directory")) {
					fileSystem.setCurrent((Directory) child);
					foundDir = true;
					System.out.println(
							"Successfully changed the current directory to " + fileSystem.getCurrent().getName());
				}
			}
			if (!foundDir) {
				System.out.println("directory " + changeTo + "doesnot exist in " + fileSystem.getCurrent().getName());
			}
		} else if (changeTo != null && changeTo.equals("..")) {
			Directory currentDirectory = fileSystem.getCurrent();

			Directory parent = currentDirectory.getParent();
			if (parent != null) {
				fileSystem.setCurrent(parent);
				System.out
				.println("Successfully changed the current directory to " + fileSystem.getCurrent().getName());
			} else {
				Directory root = fileSystem.getRoot();
				fileSystem.setCurrent(root);
				System.out
				.println("Successfully changed the current directory to " + fileSystem.getCurrent().getName());
			}

		} else {
			Directory root = fileSystem.getRoot();
			fileSystem.setCurrent(root);
			System.out.println("Successfully changed the current directory to " + fileSystem.getCurrent().getName());

		}

	}

	@Override
	public String getName() {
		return ChangeDirectory.name;
	}

}
