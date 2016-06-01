package facade;

import java.util.Date;

public abstract class FSElement {
	
	private Directory parent;
	protected String type;
	protected String name;
	protected String owner;
	private Date created;
	private Date lastModified;
	protected int size;
	
	public FSElement(Directory parent,String name, String owner, int size) {
		this.parent = parent;
		this.name = name;
		this.owner = owner;
		this.created = new Date();
		this.size = size;
		if(parent!=null){
			parent.appendChild(this);
			}
	}

	public Directory getParent() {
		return parent;
	}

	public void setParent(Directory parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getCreated() {
		return created;
	}

	
	public abstract String getType();
	public abstract int getSize();
	public abstract boolean isLeaf();
	public abstract int getDiskUtil();
	
	

}
