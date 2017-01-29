import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads in a file. 
 * 
 * @author DannyR
 *
 */
public class FileReader {
	private String fileName;
	private ArrayList<String> lines;
	
	
	public FileReader(String file){
		fileName = file;
		
		lines = new ArrayList<String>();
		
		readFile();
	}
	/**
	 * reading the file and putting the lines into an ArrayList
	 */
	public void readFile(){
		try{
			File myFile = new File(fileName);
			Scanner in = new Scanner(myFile);
			
			while(in.hasNextLine()){
				String line = in.nextLine();
				lines.add(line);
			}
			in.close();
		}
		
		catch(FileNotFoundException f){
			f.printStackTrace();
			
		}
	}
	/**
	 * Accessor method for lines ArrayList
	 * @return lines Arraylist
	 */
	public ArrayList<String> getLines(){
		return lines;
	}
	
	
}
