package facade;

import java.util.Comparator;

public class ReverseAlphabeticalOrder implements Comparator<FSElement> {

	@Override
	public int compare(FSElement o1, FSElement o2) {
		
		return o2.getName().compareTo(o1.getName());
	}

	

}
