package facade;

public class Link extends FSElement {
	
	private FSElement target;

	public Link(Directory parent,FSElement target, String name, String owner) {
		super(parent, name, owner, 0);
		this.target = target;
		this.type = "Link";
	
	}

	@Override
	public String getType() {
		
		return this.type;
	}

	@Override
	public int getSize() {
		return target.getSize();
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	public FSElement getTarget() {
		return target;
	}

	public void setTarget(FSElement target) {
		this.target = target;
	}

	@Override
	public int getDiskUtil() {
			return 0;
	}


}
