import java.util.ArrayList;
import java.util.HashMap;

public class BikeDuration {

	HashMap<Integer, ArrayList<Integer>> duration = new HashMap<Integer, ArrayList<Integer>>();
	
	
	public BikeDuration(){
		parseFile();
	}
	
	FileReader trips = new FileReader("/Users/ma"
			+ "rilynrosenberg/Google Drive/Homework04/src/Q3_2016_trips.csv");
	ArrayList<String> lines = trips.getLines();
		
		
		 /**
		 * Question 5
		 * This method parses the file and fills the hashmap of trip numbers and trip data.
		 * 
		 **/
		public void parseFile(){
			for (int i = 1; i < lines.size(); i++){
				String[] data = lines.get(i).split(",");
				Values myTrips = new Values();
				
				int bikeID = Integer.parseInt(data[10]);
				int singleRide = Integer.parseInt(data[1]);
				
				if(duration.containsKey(bikeID) == false){
					duration.put(bikeID, new ArrayList<Integer>());
					duration.get(bikeID).add(singleRide);
				}
				else{
					duration.get(bikeID).add(singleRide);
							}
	
}
			
		}
		/**
		 * This method goes through the duration hashmap, adds the elements of each arraylist, 
		 * and returns the key with the largest value
		 * @return the id of the bike that has traveled the longest
		 */
		public int longestTraveler(){

			int currentLongest = 0; //The ID of the bike that has traveled the longest so far
			int longestDuration = 0;	
			
				for(Integer key : duration.keySet()){
					int singleBikeDuration = 0;
					ArrayList<Integer> tempValues = duration.get(key);
				for(int i = 0; i < tempValues.size() ; i++){
					singleBikeDuration += tempValues.get(i);
			
				}
				if(singleBikeDuration > longestDuration){
					longestDuration = singleBikeDuration;
					currentLongest = key;
					}
				
				
		}
				return currentLongest;
	}
		
		
		
		
		
}