package Code;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataText {
	
	public void createFile(String fileName, String directory) {
		
		String absolutePath = directory + File.separator + fileName;
		
		try {
			
			File newFile = new File(absolutePath);
			
			if(newFile.createNewFile()) {
				System.out.print("File was created in " + absolutePath + ".");
			}
			
			else {
				System.out.print("File already exists at location.");
			}
			
		}
		catch (IOException e) {
			System.out.print("Failed File Creation");
		}
	
	}
	
	
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
	
	public ArrayList<String> textToArray(String fileName, String directory) {
		
		String absolutePath = directory + File.separator + fileName;
		
		ArrayList<String> ret = new ArrayList<String>();
		System.out.println(absolutePath);
		try {
			ret = (ArrayList<String>) Files.readAllLines(Paths.get(absolutePath));
			
//			System.out.println(ret.get(3));
			
//			return ret;
		}
		catch (IOException e) {
			System.out.print("Failed");
		}
		return ret;
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
