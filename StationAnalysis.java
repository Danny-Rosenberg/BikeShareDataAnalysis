import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class StationAnalysis {

	HashMap<Integer, ArrayList<String>> stationData = new HashMap<Integer, ArrayList<String>>();
	
	public StationAnalysis(){
		parseFile();
	}
	
	FileReader stations = new FileReader("/Users/marilynrosenberg/Google Drive/Homework04/src/Station-Table.csv");
	ArrayList<String> lines = stations.getLines();
	/**
	 * Creating a hashmap with line number as key and the rest of the information about that station in an arrayList of Strings
	 */
	public void parseFile(){
		for (int i = 0; i < lines.size(); i++){
			ArrayList<String> data = new ArrayList<String>();
			data.addAll(Arrays.asList(lines.get(i).split(",")));  
			if (data.size() > 4){
				String placeHolder = data.get(1) + data.get(2);
				data.remove(2);
				data.set(1, placeHolder);
			}
			Values myStations = new Values();
			for(int a = 0; a < data.size(); a++){ 
				myStations.rowInfo.add(data.get(a));
				
			}
			stationData.put(i, myStations.rowInfo);
		}
		
	}
	
	/**
	 * Stations that were the only ones to be activated on their go-live date. 
	 */
	public void uniqueGoLive(){
		ArrayList<String> uniqueStations = new ArrayList<String>();
		ArrayList<String> notUniqueStations = new ArrayList<String>();
		for(int i = 1; i < stationData.size(); i++){
			ArrayList<String> tempVar = stationData.get(i);
			if(uniqueStations.contains(tempVar.get(2)) == false){
				uniqueStations.add(tempVar.get(2));
			}
			else{
				notUniqueStations.add(tempVar.get(2));
			}
		}
		
		ArrayList<String> temp = new ArrayList<String>(uniqueStations);
		for(String element : temp){
			if(notUniqueStations.contains(element)){ 
				uniqueStations.remove(element);
			}
		
		}
		
		ArrayList<String> uniqueIDs = new ArrayList<String>();
		for(int i = 1; i < stationData.size(); i++){
			for(String element : uniqueStations){
				if (stationData.get(i).get(2).equals(element)){
					uniqueIDs.add(stationData.get(i).get(0));
				}
			}
		}
		
		System.out.println(uniqueIDs);
		
		printUniqueStations(uniqueIDs);
	}
	/**
	 * Printing the trips that correspond to unique-date stations
	 * @param uniqueStations
	 */
	public void printUniqueStations(ArrayList<String> uniqueIDs){
		TripAnalysis trips = new TripAnalysis();
		for(int i = 1 ; i < trips.tripData.size(); i++){ //goes through all of trips 
			ArrayList<String> tempVar = trips.tripData.get(i);
			for(int a = 0; a < uniqueIDs.size(); a++) {//iterate through unique stations ArrayList
				String startStation = tempVar.get(4);
				String endStation = tempVar.get(7);
				String stationId = uniqueIDs.get(a);
				boolean first = startStation.contains(stationId);
				boolean second = endStation.contains(stationId);
			if(first || second){ //check if these locations contain a 
				//System.out.println(trips.tripData.get(i).get(0)); //print the trips that correspond to unique-date stations
			}
			}
		}
	}
	
	/**
	 * How many stations in a certain year are still active
	 * @param year the year 
	 * @return the number of stations from this year that are still active
	 */
	public int activeStations(String year){
		int active = 0;
		for (int i = 1; i < lines.size(); i++){
		ArrayList<String> tempVar = stationData.get(i);
			if(tempVar.get(2).contains(year) && tempVar.get(3).equals("Active")){
				active++;
			}
		}
		return active;
	}
	
}
