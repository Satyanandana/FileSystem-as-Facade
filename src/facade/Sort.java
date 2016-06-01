package facade;

import java.util.Collections;

public class Sort implements Command {
	private static final String name = "sort";
	private FileSystem fileSystem = FileSystem.getfileSystem();
	private String sortType;
    
	public Sort(String sortType) {
		this.sortType = sortType;
	}
	
	@Override
	public void execute() {
		if(sortType!=null){
			if(sortType.equals("alpha")){
				Collections.sort(fileSystem.getCurrent().getChildren(), new AlphabeticalOrder());
				System.out.println("Sorted the elements in current directory in Alphabetical order");
			}else if(sortType.equals("revalpha")){
				Collections.sort(fileSystem.getCurrent().getChildren(), new ReverseAlphabeticalOrder());
				System.out.println("Sorted the elements in current directory in reverse Alphabetical order");
			}
			
		}else{
		Collections.sort(fileSystem.getCurrent().getChildren(), new AlphabeticalOrder());
		System.out.println("Sorted the elements in current directory in Alphabetical order");
		}
	}

	@Override
	public String getName() {
		return Sort.name;
	}

}
