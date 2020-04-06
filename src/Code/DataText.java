package Code;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataText {
	
	public void updateText(String fileName, String directory, String name, String ID){
		
		String absolutePath = directory + File.separator + fileName;
		
		try {
			
			FileWriter fileWriter = new FileWriter(absolutePath);
			
			fileWriter.write(name + ID + System.lineSeparator());
				
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateArray(ArrayList<String> saveFile, String title, String name, String ID) {
		
		saveFile.add(title + "," + name + "," + ID);
		
	}
	
	public void textToArray(ArrayList<String> testName, String fileName, String directory) {
		
		String absolutePath = directory + File.separator + fileName;
		
		try {
			testName = (ArrayList<String>) Files.readAllLines(Paths.get(absolutePath));
		}
		catch (IOException e) {
			System.out.print("Failed");
		}
	}	
}
