import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will make a hashmap with year as key and type of trip as value. 
 * @author DannyR
 *
 */
public class TripType {
	
	private String year;
	private String type;
	
	//I'd like my constructor to be in this form: where you can pass in
	//the values you want to query, and it will parse what you input
	public TripType(String year, String type){
		this.year = year;
		this.type = type;
		
//		createHashMap();
		createArrayLists();
		numberWalkUp();
		
	}

	
	HashMap<String, String> yearTripType = new HashMap<String, String>();
	
//	FileReader trips = new FileReader("/Users/marilynrosenberg/Google Drive/Homework04/src/Q3_2016_trips.csv");
//	ArrayList<String> lines = trips.getLines();

//	FileReader stations = new FileReader("/Users/marilynrosenberg/Google Drive/Homework04/src/Station-Table.csv");
//	ArrayList<String> lines = stations.getLines();
	int numberLines = lines.size();
			
	/**
	 * This method fills a hashmap with desired keys and values
	 */
	public void createHashMap(){
	for (int i = 1; i < lines.size(); i++){
		String[] data = lines.get(i).split(",");
		if(data[2].equals(year)){
		yearTripType.put(data[2], data[3]);
		}
		}
	System.out.println(yearTripType);
	}
	
	public void createArrayLists(){
		ArrayList<String> data1 = new ArrayList<String>();
		for (int i = 1; i < lines.size(); i++){
			String[] data = lines.get(i).split(",");
			for(String date : data){
				data1.add(data[2]);
			}
			System.out.println(data1);
		}
	}
	
	//Can't do hashmap for number 2 -- duplicate keys and values
	//So does this mean for number 1 if riders checked out the bikes at the exact same time they weren't both counted?
	
	
	public void numberWalkUp(){
		int counter = 0;
		for(String types : yearTripType.values()){
			if(types.contains(type))
				counter++;
			}
		System.out.println("The number of stations in " + year + " that are still active are: " + counter);
		
		}
		
	}
			
	
	

	

