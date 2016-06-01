package facade;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		
		FileSystem fileSystem = FileSystem.getfileSystem();
		Directory root = fileSystem.getRoot();
		
		Directory system = new Directory(root, "system", "satya");
		File a = new File(system, "a.txt", "satya", 30);
		File b = new File(system, "b.pdf", "satya", 60);
		File c = new File(system, "c.txt", "satya", 80);
		Directory home = new Directory(root, "home", "satya");
		File d = new File(home, "d.pdf", "satya", 45);
		Link x = new Link(home, system, "Link to system directory", "satya");
		Directory picture = new Directory(home, "picture", "satya");
		File e = new File(picture, "e.pdf", "satya", 45);
		File f = new File(picture, "f.txt", "satya", 65);
		Link y = new Link(picture, e, "Link to file e", "satya");
		Link z = new Link(picture, x, "Link to link x", "satya");
		
		Shell shell = new Shell();
		shell.showCommandList();
		shell.run();

	}

}
