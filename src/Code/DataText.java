package Code;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataText {
	//creates text file at specified location
	public void createFile(String fileName, String directory) {
		//gets current working directory
		String absolutePath = directory + File.separator + fileName;
		//Creates new file object and creates the file at location
		try {
			
			File newFile = new File(absolutePath);
			
			if(newFile.createNewFile()) {
				System.out.print("File was created in " + absolutePath + ".");
			}
			
			else {
				System.out.print("File already exists at location.");
			}
			
		}
		//catches location
		catch (IOException e) {
			System.out.print("Failed File Creation");
		}
	
	}
	
	//Updates text file with parameters
	public void updateText(String fileName, String directory, String name, String ID){
		
		//directory to file and file name combined.
		String absolutePath = directory + File.separator + fileName;
		//updates given file with parameters
		try {
			
			FileWriter fileWriter = new FileWriter(absolutePath);
			
			fileWriter.write(name + ID + System.lineSeparator());
				
			fileWriter.close();
		}
		//catches errors
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	//adds parameters to the given array list
	public void updateArray(ArrayList<String> saveFile, String title, String name, String ID) {
		
		saveFile.add(title + "," + name + "," + ID);
		
	}
	//Converts text in textfile to strings in a given arraylist
	public ArrayList<String> textToArray(String fileName, String directory) {
		//gets current working directory
		String absolutePath = directory + File.separator + fileName;
		
		ArrayList<String> ret = new ArrayList<String>();
		System.out.println(absolutePath);
		try {
			ret = (ArrayList<String>) Files.readAllLines(Paths.get(absolutePath));	
			
//			System.out.println(ret.get(3));
			
//			return ret;
		}
		//catches errors
		catch (IOException e) {
			System.out.print("Failed");
		}
		return ret;
	}
	//converts arraylist elements to text in given text file
	public void arrayToText (ArrayList<String> arrayName, String fileName, String directory ) {
		//gets current working directory
		String absolutePath = directory + File.separator + fileName;
		//opens a fileWriter and writes arraylist elements to text file
		try {
			FileWriter writer = new FileWriter(absolutePath);
			for (String str: arrayName) {
				writer.write(str + System.lineSeparator());
			}
			//closes filewriter
			writer.close();
		}
		//catches exceptions
		catch (IOException e) {
			System.out.print("Failed");
		}
		
	}
}
