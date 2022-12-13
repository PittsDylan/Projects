package com.snhu.Milestoneapp.Models;


/**
 * model for flight search data
 * 
 * Get search results that will be used to
 * Return a flight itinerary
 * 
 * Possible update: arrival is not in use implement
 * arrival as additional search parameter.
 */
public class SearchModel {

    private String departure;   //Holds Departure flight data
    private String arrival;     //holds Arrival flight data

    //Getters and Setters
    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
  
}
