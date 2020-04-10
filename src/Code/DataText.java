package Code;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataText {
	
	public void updateText(String fileName, String directory, String name, String ID){
		
		//directory to file and file name combined.
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
	
	public void textToArray(ArrayList<String> arrayName, String fileName, String directory) {
		
		String absolutePath = directory + File.separator + fileName;
		
		try {
			arrayName = (ArrayList<String>) Files.readAllLines(Paths.get(absolutePath));
		}
		catch (IOException e) {
			System.out.print("Failed");
		}
	}
	
	public void arrayToText (ArrayList<String> arrayName, String fileName, String directory ) {
		
		String absolutePath = directory + File.separator + fileName;
		
		try {
			FileWriter writer = new FileWriter(absolutePath);
			for (String str: arrayName) {
				writer.write(str + System.lineSeparator());
			}
			writer.close();
		}
		catch (IOException e) {
			System.out.print("Failed");
		}
		
	}
}
