import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.crypto.Data;

public class TripAnalysis {

	HashMap<Integer, ArrayList<String>> tripData = new HashMap<Integer, ArrayList<String>>();

	
	public TripAnalysis(){
		parseFile();
	
	}

	
	FileReader trips = new FileReader("/Users/marilynrosenberg/Google Drive/Homework04/src/Q3_2016_trips.csv");
	ArrayList<String> lines = trips.getLines();
	
	
	
	/**
	 * This method parses the file and fills the hashmap of trip numbers and trip data.
	 */
	public void parseFile(){
		for (int i = 1; i < lines.size(); i++){
			String[] data = lines.get(i).split(",");
			Values myTrips = new Values();
			for(int a = 0; a < data.length; a++){ 
				myTrips.rowInfo.add(data[a]);
				
			}
			tripData.put(i, myTrips.rowInfo);
		}
		
	}

	
	/**
	 * Type of trips in a particular year
	 */
	public int typeTripYear(String year, String tripType){
		int walkUps = 0;
		for(int i = 1; i < tripData.size(); i++){
			ArrayList<String> data = tripData.get(i); 
			if (data.get(2).contains(year) && data.get(13).contains(tripType)){ 
				walkUps++;
			}
		}
		return walkUps;
	}
	
	/**
	 * 
	 * 
	 * User can enter station ID of the station they are interested in,
	 * method returns the percentage of trips that started in that station
	 * @param stationID The station ID
	 */
	public double tripStart(int stationID){
		double tripStartingLocation = 0.0;
		for(int i = 1; i < tripData.size(); i++){
			ArrayList<String> tempVar = tripData.get(i);
			if(tempVar.get(4).equals(Integer.toString(stationID))){
				tripStartingLocation++;
			}
		}
		return tripStartingLocation/tripData.size();
	}
	
	/**
	 * Querying the type of subscription associated with different rides
	 * @param subType the subscription type
	 * @param tripType round-trip or single ride
	 * @return the percentage of the indicated trip type and subscription type
	 */
	public double subscriptionTripType(String subType, String tripType){
		int subCounter = 0;
		int subAndTripCounter = 0;
		for(int i = 1; i < tripData.size(); i++){ 
			ArrayList<String> tempVar = tripData.get(i);
			if(tempVar.get(13).equals(subType)){
				subCounter++;
				if(tempVar.get(12).equals(tripType)){
					subAndTripCounter++;
				}
			}
		}
		return (double)subAndTripCounter/subCounter;
		
	}
	
	
	
	/**
	 * How many bikes were in use at a certain time.
	 * @param an integer between 100 and 2359 used to indicate a time during the 24 hour day
	 * @param date, the date you want to know about
	 * @return the number of bikes in use at the indicated time 
	 */
	public int bikesInUse(int time, String date){
		int bikesInUseCounter = 0;
	
		for(int i = 1; i < tripData.size(); i++){
			ArrayList<String> adjustedTrip = new ArrayList<String>();
				adjustedTrip.addAll(Arrays.asList(tripData.get(i).get(2).split(" ")));
				adjustedTrip.addAll(Arrays.asList(tripData.get(i).get(3).split(" ")));
				if(adjustedTrip.get(0).contains(date)){ //"8/3/2016"
				String startTime = new String();
				String endTime = new String();
				startTime = adjustedTrip.get(1).replace(":", ""); 
				endTime = adjustedTrip.get(3).replace(":", "");
				Double numberStartTime = Double.parseDouble(startTime);
				Double numberEndTime = Double.parseDouble(endTime);
				if(numberStartTime <= time && numberEndTime > time){
					bikesInUseCounter++;
				}
				}
				
			}
		return bikesInUseCounter;
		}
		
	
	
	
	
	
	
	
	
	
	/**
	 * Printing out all the information for the longest single trip by distance
	 * @return ArrayList of the information about the longest trip
	 */
	public ArrayList<String> longestDistance(){
		HashMap<Double, ArrayList<String>> longestTrip = new HashMap<Double, ArrayList<String>>();
		for (int i = 1; i < tripData.size(); i++){
			ArrayList<String> tempVar = tripData.get(i);
			
			try{
			String startLon = tempVar.get(6);
			String startLat = tempVar.get(5);
			String endLon = tempVar.get(9);
			String endLat = tempVar.get(8);
			
			
			Double Lon = Double.parseDouble(endLon) - Double.parseDouble(startLon);
			Double Lat = Double.parseDouble(endLat) - Double.parseDouble(startLat);
			
			Double lonSQ = Lon * Lon;
			Double latSQ = Lat * Lat;
			
			
			Double totalDistance = lonSQ + latSQ;
			totalDistance = Math.sqrt(totalDistance);
			
			longestTrip.put(totalDistance, tripData.get(i));}
			catch(NumberFormatException e){
				continue;
			}
			
		}
		double largestKey = 0;
		for (double key : longestTrip.keySet()){
			if (key > largestKey){
				largestKey = key;
			}
			
		}
		System.out.println(largestKey);
		return longestTrip.get(largestKey);
	}
	
	
	
	/**
	 * Finding the bike that is used the least in the data set
	 */
	public String worstBike(){
//		HashMap<Integer, ArrayList<String>> modifiedTripData = new HashMap<Integer, ArrayList<String>>();
		HashMap<String, Integer> bikeAndCount = new HashMap<String, Integer>();
		for(int i = 1; i < tripData.size(); i++){
			String bikeId = tripData.get(i).get(10);
			int bikeCounter = 0;
			bikeAndCount.put(bikeId, bikeCounter);
			
		}
			for(int i = 1; i < tripData.size(); i++){  //go through trip data again
				String bikeIDTemp = tripData.get(i).get(10);
				bikeAndCount.put(bikeIDTemp, bikeAndCount.get(bikeIDTemp) + 1);
				
			}
			System.out.println(bikeAndCount);
			int currentWorst = Integer.MAX_VALUE;
			String worstBike = new String();
			for(String element : bikeAndCount.keySet()){
				if (bikeAndCount.get(element) < currentWorst){
					currentWorst = bikeAndCount.get(element);
					worstBike = element;
				}
			}
			System.out.println(bikeAndCount.size());
			return worstBike;
	}
	
	
	
	
}
