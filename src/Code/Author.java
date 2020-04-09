package Code;

import java.util.ArrayList;

public class Author extends User {

	public static ArrayList<Journal> myJournals = new ArrayList<Journal>();
	
	public static AuthorGUI myPerspective = new AuthorGUI();
	
	//For running independently
	public static void main(String[] args) {
		myPerspective.open();
	}
	
	public static void openPerspective() {
		myPerspective.open();
	}
	
	public static void upload() {
		
	}
	
}