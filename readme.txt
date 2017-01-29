Regarding design, I stuck closely to my original CRC. In retrospect, it would have better/clearer to make classes that stored all the values of a trip or a station as instance variables in classes with these respective names. Currently my methods rely on getting information from column indexes, which makes the code less readable. In places I tried to simplify long method chains by placing them in variables, and then calling additional methods on those variables. 

The only question-answering class that falls outside of station analysis and trip analysis is bike duration. I made this class to answer question 5 because I was getting errors when trying to add values to an ArrayList from the tripData hashmap. Unsure of where my errors were originating, I just created a new class and parsed the file from scratch in the style I needed to answer the question.

Another redundancy in my code is the ‘values’ class - I used it initially to populate the tripData HashMap, but in retrospect I could’ve just used an empty ArrayList instead. 


1. The number of walk-ups in the third quarter of 2016 was: 39961.
This algorithm (method ‘typeTripYear’) takes in a String for year and a String for trip type. It then iterates through the tripData hashmap, and checks if the year and trip type can be found in the same trip. If so, the counter increments by one, and the method returns the counter when the loop is finished. 

2. The number of stations that had a Go-live data in 2015 that are still active is 73. Similar to question, the method ‘activeStations’ iterates through a hashmap of station information, and increments a counter whenever the indicated year is found in the third column, and the same row also contains the string “active” in the fourth column.

3. About 4.27% of all trips started in Rittenhouse Square. 
This method ‘tripStart’ receives a station ID integer, and looks for it in the tripData hashmap (this could have also been done with passing in a string station ID, but it seemed more intuitive from a UX perspective to enter an integer). The iterator looks in the 5th column of the trip data arrayList, parses the String into an int, and if it equals the passed in stationID, the counter increments. Finally the method returns the quotient of the counter and the total number of lines.

4. The percentage of Indego30 riders that are round trips is about 5.55%.
This method, subscriptionTripType, takes in a subscription type String, and a tripType String. The method iterates through the trip data hashmap. It first checks if the subscription type can be found in the 13th column of the ArrayList. If it is found, a counter is incremented, and the method goes into a second if statement that checks if the preceeding column contains the desired trip type String. If so, a second incrementer increases. The method returns a quotient of the number of trips that satisfy both the subscription type and trip type, and the total number of indicated subscription types. 

5. The ID of the bike that has traveled the most in terms of duration is 2640. 
This method has its own class called ‘bike duration’ because I was running into problems trying to take values from the trip data hashmap. So, I created a new hashmap (with parseFile in the BikeDuration Class) consisting of rows of bike ids as keys and an ArrayList of ints of duration single ride durations as my values. Then, I used the method ‘longestTraveler’ to see which bike had the longest total duration. I did this by iterating through the ArrayList in my hashmap (duration), and used a nested for loop to add all the values within each arrayList, and compare this sum with the previously longest duration. If this sum was the longest, it replaces the longest duration. The method then returns the ID of the bike with the longest duration.

6. On 8/3/16 at 7:00am, there were 18 bikes in use.
This method, ‘bikesInUse’ iterates through the tripData hashmap, and splits the start time column and the end time column by the space “ “ between the date and time. It stores these now 4 Strings in an ArrayList called ‘adjusted trip’. If the first value in adjusted trip ArrayList contains the desired date, the method replaces the colons time values in the ArrayList with nothing, and then parses them into doubles, thus turning a time like 6:52 into double 652.0. If the start time is less than or equal to 700 and the end time is greater than 700, then the bike is considered ‘in use’, and a counter is incremented. The counter is returned. 

7. The information for the longest trip by distance: [44945662, 2280, 9/9/2016 23:27, 9/10/2016 0:05, 3117, 39.978409, -75.223991, 3041, 39.968491, -75.13546, 11036, 0, One Way, Walk-up].
This method ‘longestDistance’ creates a hashMap consisting of a double of the total distance of a single trip, and an arrayList of all of the information about the trip. The method first iterates over the tripData ArrayList, and creates four variables from the longitudes and latitudes in the trip, and then calculates the total distance of the trip, and puts it in the hashmap. Once the hashmap is filled, it executes an enhanced for loop that goes through the hashmap’s keys (distances). If it encounters the largest key so far, it stores this value in the largestKey variable outside of the loop. Finally, the method returns the value associated with the largest key. 

8. This quesiton involves two methods in the ‘station analysis’ class: ‘uniqueGoLive’ and ‘printUniqueStations’. The uniqueGoLive method begins by creating two empty ArrayLists, ‘uniqueStations’ and ‘notUniqueStations’, and iterating through the stationData hashmap (lines number and an ArrayList of station information). If the iterator encounters a station ID not within uniqueStations, it adds it to uniqueStations. If the station is already in uniqueStations, then it is added to ‘notUniqueStations’. 
Next, the method creates a placeholder ArrayList called ‘temp’ and is assigned all the same values as uniqueStations. Then the method executes an enhanced for loop in this temp ArrayList. If notUniqueStations contains an element in temp, that value will be removed from uniqueStations. This third ArrayList is used to prevent a concurrency exception. 
Then, yet another ArrayList is created called UniqueIDs, because as of now we’ve just been storing the dates these stations were activated, not actual station IDs. So now we go through the uniqueStations ArrayList, and get the corresponding stationID of each and add it to uniqueIDs.
Finally, we call ‘printUniqueStations’, passing in uniqueIDs as the argument. This method creates a TripAnalysis object, so we can use its hashmap and ArrayList of trip values. We iterate through the tripData, and at each line we iterate through the unique stations, checking if either the start station or the end station contain the the unique station ID. If so, the method will print out the corresponding trip number.

9. *Wildcard question
I decided to find the ID of the ‘worst bike’ as measured by the fewest number of trips. It turns out this bike is ID 11037, with only 3 trips. I found this with the method ‘worstBike’ in tripAnalysis. I first created a new hashmap ‘bikeAndCount that would hold a bikeID as the key, and a counter as the value. Then I made a for loop that goes through the tripData arrayList and increments the value of the bikeAndCount hashmap whenever the corresponding bike ID is encountered. Finally I made another loop that compares the values and finds the smallest one, and returns the bikeID associated with that low value. 
The reason I named this method ‘worstBike’ is that I’m assuming that bikes that have the least rides are somehow undesirable - either they are disfunctional or they appear to be. The city can use this method to which bikes are not being used, so they can pinpoint their repair efforts. 



 


