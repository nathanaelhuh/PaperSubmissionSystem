package Code;

import java.io.*;
import java.nio.file.*;


public class AuthorFunctions {
	//code referenced from geeksforgeeks.org
	public void authorMoveFiles(String file_name, String move_directory) throws IOException {

		Path temp =	Files.move(Paths.get( new java.io.File(".").getCanonicalPath() + File.separator + file_name), Paths.get(move_directory), StandardCopyOption.REPLACE_EXISTING);

		
		if(temp != null) {
			System.out.print("File Moved");
		}
		else {
			System.out.print("File Failed To Move");
		}
	}
	//code referenced from geeksforgeeks.org
	public void authorCPFiles(String file_name, String move_directory) throws IOException {
		
		try {
			Files.copy(Paths.get( new java.io.File(".").getCanonicalPath() + File.separator + file_name), Paths.get(move_directory), StandardCopyOption.REPLACE_EXISTING);
			
		}catch (IOException e) {
			System.out.println("Failed");
		}
		
	}
}
