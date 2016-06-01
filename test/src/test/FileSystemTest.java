package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import facade.CommandHistory;
import facade.Directory;
import facade.FSElement;
import facade.File;
import facade.FileSystem;
import facade.Link;
import facade.Shell;
import junit.framework.AssertionFailedError;



public class FileSystemTest {
	
	FileSystem fileSystem ;
	Directory root ;
	
	File p ;
	Directory system ;
	File a ;
	File b ;
	File c ;
	Date createdDate;
	Directory home ;
	File d ;
	Link x;
	Directory picture ;
	File e ;
	File f ;
	Link y;
	Link z;
	Directory music ;
	Shell shell;
	CommandHistory commandHistory;
	
	
	@Before
	public void initialize(){
		shell = new Shell();
		commandHistory = CommandHistory.getInstance();
		commandHistory.getHistory().clear();
		fileSystem = FileSystem.getfileSystem();
		root = new Directory(null, "root", "Satya");
		fileSystem.setRoot(root);
		fileSystem.setCurrent(root);
		
		p = new File(root, "p.txt", "satya", 30);
		system = new Directory(root, "system", "satya");
		a = new File(system, "a.txt", "satya", 30);
		b = new File(system, "b.pdf", "satya", 60);
		c = new File(system, "c.txt", "satya", 80);
		createdDate = new Date();
	    home = new Directory(root, "home", "satya");
		d = new File(home, "d.pdf", "satya", 45);
		x = new Link(home, system, "Link to system directory", "satya");
		picture = new Directory(home, "picture", "satya");
		e = new File(picture, "e.pdf", "satya", 45);
		f = new File(picture, "f.txt", "satya", 65);
		y = new Link(picture, e, "Link to file e", "satya");
		z = new Link(picture, x, "Link to link x", "satya");
		music = new Directory(home, "music", "satya");
		fileSystem.showElement(root);
		
	}
	
	@Test
	public void testCommandRmdir(){
		Directory actual = null;
		shell.triggerCommand("mkdir",new String[]{"mkdir","newDir"});
		shell.triggerCommand("rmdir",new String[]{"rmdir","newDir1"});
		shell.triggerCommand("rmdir",new String[]{"rmdir","newDir"});
		ArrayList<FSElement> childern = fileSystem.getCurrent().getChildren();
		for(FSElement element:childern){
			if(element.getName().equals("newDir")&&element.getType().equals("Directory")){
				actual = (Directory) element;
			}
		}
		assertNull(actual);
	}
	
	@Test
	public void testCommandMkdir(){
		Directory actual = null;
		shell.triggerCommand("mkdir",new String[]{"mkdir","newDir"});
		shell.triggerCommand("mkdir",new String[]{"mkdir","newDir"});
		ArrayList<FSElement> childern = fileSystem.getCurrent().getChildren();
		for(FSElement element:childern){
			if(element.getName().equals("newDir")&&element.getType().equals("Directory")){
				actual = (Directory) element;
			}
		}
		assertNotNull(actual);
	}
	
	@Test
	public void testCommandLn(){
		Link actual = null;
		shell.triggerCommand("ln",new String[]{"ln","picture","newlink"});
		shell.triggerCommand("ln",new String[]{"ln","home","newlink"});
		shell.triggerCommand("ln",new String[]{"ln","home","newlink"});
		ArrayList<FSElement> childern = fileSystem.getCurrent().getChildren();
		for(FSElement element:childern){
			if(element.getName().equals("newlink")&&element.getType().equals("Link")){
				actual = (Link) element;
			}
		}
		assertNotNull(actual);
	}
	
	
	@Test
	public void TestCommandSort(){
		shell.triggerCommand("sort",new String[]{"sort"});
		ArrayList<FSElement> actualChildren = fileSystem.getCurrent().getChildren();
		ArrayList<FSElement> ExpectedChildren = new ArrayList<>();
		ExpectedChildren.add(home);
		ExpectedChildren.add(p);
		ExpectedChildren.add(system);
		assertArrayEquals(actualChildren.toArray(), ExpectedChildren.toArray());		
	}
	
	@Test
	public void TestCommandSortAlpha(){
		shell.triggerCommand("sort",new String[]{"sort","alpha"});
		ArrayList<FSElement> actualChildren = fileSystem.getCurrent().getChildren();
		ArrayList<FSElement> ExpectedChildren = new ArrayList<>();
		ExpectedChildren.add(home);
		ExpectedChildren.add(p);
		ExpectedChildren.add(system);
		assertArrayEquals(actualChildren.toArray(), ExpectedChildren.toArray());		
	}
	
	@Test
	public void TestCommandSortReverseAlpha(){
		shell.triggerCommand("sort",new String[]{"sort","revalpha"});
		ArrayList<FSElement> actualChildren = fileSystem.getCurrent().getChildren();
		ArrayList<FSElement> ExpectedChildren = new ArrayList<>();
		ExpectedChildren.add(system);
		ExpectedChildren.add(p);
		ExpectedChildren.add(home);
		
		assertArrayEquals(actualChildren.toArray(), ExpectedChildren.toArray());		
	}
	
	
	
	@Test
	public void testCommandRedo(){
		shell.triggerCommand("cd",new String[]{"cd"});
		shell.triggerCommand("redo",new String[]{"redo"});
		String actual = fileSystem.getCurrent().getName();
		String expected = "root";
		assertThat(actual,is(expected));
	}
	
	@Test
	public void TestCommandPwd(){
		shell.triggerCommand("pwd",new String[]{"pwd"});
		String actual = fileSystem.getCurrent().getName();
		String expected = "root";
		assertThat(actual,is(expected));		
	}

	
	@Test
	public void TestCommandLs(){
		shell.triggerCommand("ls",new String[]{"ls"});
		ArrayList<FSElement> actualChildren = fileSystem.getCurrent().getChildren();
		ArrayList<FSElement> ExpectedChildren = new ArrayList<>();
		ExpectedChildren.add(home);
		ExpectedChildren.add(p);
		ExpectedChildren.add(system);
		assertArrayEquals(actualChildren.toArray(), ExpectedChildren.toArray());		
	}
	
	@Test
	public void TestComamndCd(){
		shell.triggerCommand("cd",new String[]{"cd","home"});
		Directory actual = fileSystem.getCurrent();
		Directory expected = home;
		assertThat(actual,is(expected));
		shell.triggerCommand("cd",new String[]{"cd",".."});
		actual = fileSystem.getCurrent();
		expected = root;
		assertThat(actual,is(expected));
		shell.triggerCommand("cd",new String[]{"cd",".."});
		actual = fileSystem.getCurrent();
		expected = root;
		assertThat(actual,is(expected));
		shell.triggerCommand("cd",new String[]{"cd"});
		actual = fileSystem.getCurrent();
		expected = root;
		assertThat(actual,is(expected));
		shell.triggerCommand("cd",new String[]{"cd","music"});
		actual = fileSystem.getCurrent();
		expected = root;
		assertThat(actual,is(expected));
	}
	

		
	@Test
	public void testFileGetSize(){
		int actualSize  = a.getSize();
		int expectedSize = 30;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testFileGetDiskUtil(){
		int actualSize  = a.getDiskUtil();
		int expectedSize = 30;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testFileGetType(){
		String actualType  = a.getType();
		String expectedType = "File";
		assertThat(actualType,is(expectedType));
	}
	
	@Test
	public void testFileIsLeaf(){
		boolean actualIsLeaf  = a.isLeaf();
		boolean expectedIsLeaf = true;
		assertThat(actualIsLeaf,is(expectedIsLeaf));
	}
	
	@Test
	public void testDirectoryGetSize(){
		int actualSize  = home.getSize();
		int expectedSize = 155;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testDirectoryGetDiskUtil(){
		int actualSize  = home.getDiskUtil();
		int expectedSize = 0;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testDirectoryGetType(){
		String actualType  = home.getType();
		String expectedType = "Directory";
		assertThat(actualType,is(expectedType));
	}
	
	@Test
	public void testDirectoryIsLeaf(){
		boolean actualIsLeaf  = music.isLeaf();
		boolean expectedIsLeaf = true;
		assertThat(actualIsLeaf,is(expectedIsLeaf));
	}
	
	@Test
	public void testDirectoryGetChildren(){
		ArrayList<FSElement> actualChildren = picture.getChildren();
		ArrayList<FSElement> ExpectedChildren = new ArrayList<>();
		ExpectedChildren.add(y);
		ExpectedChildren.add(z);
		ExpectedChildren.add(e);
		ExpectedChildren.add(f);
		assertArrayEquals(actualChildren.toArray(), ExpectedChildren.toArray());
	}
	
	@Test
	public void testLinkGetSize(){
		int actualSize  = z.getSize();
		int expectedSize = 170;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testLinkGetDiskUtil(){
		int actualSize  = z.getDiskUtil();
		int expectedSize = 0;
		assertThat(actualSize,is(expectedSize));
	}
	
	@Test
	public void testLinkGetType(){
		String actualType  = x.getType();
		String expectedType = "Link";
		assertThat(actualType,is(expectedType));
	}
	
	@Test
	public void testLinkIsLeaf(){
		boolean actualIsLeaf  = y.isLeaf();
		boolean expectedIsLeaf = true;
		assertThat(actualIsLeaf,is(expectedIsLeaf));
	}
	
	@Test
	public void testLinkSetAndGetTarget(){
		y.setTarget(f);
		FSElement actualTarget = y.getTarget();
		FSElement expectedTarget = f;
		assertThat(actualTarget,is(expectedTarget));
	}
	
	@Test
	public void testFSElementGetParent(){
		music.setParent(home);
		Directory actualParent = music.getParent();
		Directory expectedParent = home;
		assertThat(actualParent,is(expectedParent));
	}
	
	@Test
	public void testFSElementGetName(){
		music.setName("music1");
		String actualName = music.getName();
		String expectedName = "music1";
		assertThat(actualName,is(expectedName));
	}
	
	@Test
	public void testFSElementGetOwner(){
		music.setOwner("varma");
		String actualName = music.getOwner();
		String expectedName = "varma";
		assertThat(actualName,is(expectedName));
	}
	
	@Test
	public void testFSElementGetCreatedDate(){
		Date actualCreatedDate = home.getCreated();
		Date expetedCreatedDate = createdDate;
		assertThat(actualCreatedDate,is(expetedCreatedDate));
	}
	
	@Test
	public void testFSElementGetLastModifiedDate(){
		Date lastModifyDate = new Date();
		music.setLastModified(lastModifyDate);
		Date actuallastModifyDate = music.getLastModified();
		assertThat(actuallastModifyDate,is(lastModifyDate));
	
	}
	
	@Test
	public void testFSElementSetRoot(){
		fileSystem.setRoot(home);
		Directory actualRoot = fileSystem.getRoot();
		Directory expectedRoot = home;
		fileSystem.showAllElements(home);
		assertThat(actualRoot,is(expectedRoot));
	}
	
	@Test
	public void testCommandChown(){
		shell.triggerCommand("chown",new String[]{"chown",".","VARMA"});
		String actual = fileSystem.getCurrent().getOwner();
		String expected = "VARMA";
		assertThat(actual,is(expected));
		shell.triggerCommand("chown",new String[]{"chown","p.txt","VARMA"});
		actual = fileSystem.getCurrent().getOwner();
		expected = "VARMA";
		assertThat(actual,is(expected));
		shell.triggerCommand("chown",new String[]{"chown","system","VARMA"});
		actual = fileSystem.getCurrent().getOwner();
		expected = "VARMA";
		assertThat(actual,is(expected));
		shell.triggerCommand("chown",new String[]{"chown","music/a.pdf","VARMA"});
		actual = fileSystem.getCurrent().getOwner();
		expected = "VARMA";
		assertThat(actual,is(expected));
		shell.triggerCommand("chown",new String[]{"chown","home/a.pdf","VARMA"});
		actual = fileSystem.getCurrent().getOwner();
		expected = "VARMA";
		assertThat(actual,is(expected));
		shell.triggerCommand("chown",new String[]{"chown","home/d.pdf","VARMA"});
		actual = fileSystem.getCurrent().getOwner();
		expected = "VARMA";
		assertThat(actual,is(expected));
		shell.triggerCommand("chown",new String[]{"chown","d.pdf","VARMA"});
		actual = fileSystem.getCurrent().getOwner();
		expected = "VARMA";
		assertThat(actual,is(expected));
	}
	
	@Test
	public void testCommandHistory(){
		shell.triggerCommand("pwd",new String[]{"pwd"});
		shell.triggerCommand("ln",new String[]{"ln","picture","newlink"});
		shell.triggerCommand("mkdir",new String[]{"mkdir","newDir"});
		shell.triggerCommand("rmdir",new String[]{"rmdir","newDir"});
		shell.triggerCommand("cd",new String[]{"cd","home"});
		shell.triggerCommand("ls",new String[]{"ls"});
		shell.triggerCommand("sort",new String[]{"sort"});
		shell.triggerCommand("chown",new String[]{"chown",".","VARMA"});
		
		shell.triggerCommand("dir",new String[]{"dir"});
		shell.triggerCommand("dir",new String[]{"dir","system"});
		shell.triggerCommand("dir",new String[]{"dir","d.pdf"});
		shell.triggerCommand("dir",new String[]{"dir","picture"});
		shell.triggerCommand("dir",new String[]{"dir",".."});
		shell.triggerCommand("dir",new String[]{"dir","music/e.pdf"});
		shell.triggerCommand("dir",new String[]{"dir","picture/a.pdf"});
		shell.triggerCommand("dir",new String[]{"dir","picture/e.pdf"});
		shell.triggerCommand("history", new String[]{"history"});
		int actual = commandHistory.getHistory().size();
		int expected = 17;
		assertThat(actual,is(expected));
	}
}
