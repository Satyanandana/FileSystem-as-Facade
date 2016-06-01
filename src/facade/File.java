package facade;

public class File extends FSElement {

	public File(Directory parent, String name, String owner, int size) {
		super(parent, name, owner, size);
		this.type = "File";
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public int getDiskUtil() {
		return getSize();
	}

	

}
