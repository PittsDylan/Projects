package com.snhu.Milestoneapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)

/**
 * flight itinerary as JSON Object
 * 
 * Translates the flight API JSON as 
 * spring model.
 */
public class FlightData {

    private String id;              //itenerary identifier

    private String flyTo;           //destination airport

    private String flyFrom;         //departure airport

    private Double price;           //cost in Euro

    private String cityFrom;        //departure city

    private String cityTo;          //destination city

    private String  utc_departure;  //departure time in utc

    private String  utc_arrival;    //destination time in utc

    private JsonNode countryFrom;   //departure country includes code and name in api

    private JsonNode countryTo;     //destination country includes code and name in api

    private JsonNode availability;  //avaliable seats under availability.seats in api

    //null constructor
    public FlightData() {
        id = "";
        flyFrom = "";
        flyTo = "";
        price = 0.0;
        utc_departure = "";
        utc_arrival = "";
        countryFrom = null;
        countryTo = null;
        availability = null;
    }

    /** Start of Getters and Setters **/
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFlyTo(String flyTo) {
        this.flyTo = flyTo;
    }

    public String getFlyTo() {
        return flyTo;
    }

    public void setFlyFrom(String flyFrom) {
        this.flyFrom = flyFrom;
    }

    public String getFlyFrom() {
        return flyFrom;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }
    
    public String getUtc_departure() {
        return utc_departure;
    }

    public void setUtc_departure(String utc_departure) {
        this.utc_departure = utc_departure;
    }

    public String getUtc_arrival() {
        return utc_arrival;
    }

    public void setUtc_arrival(String utc_arrival) {
        this.utc_arrival = utc_arrival;
    }

    public JsonNode getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(JsonNode countryFrom) {
        this.countryFrom = countryFrom;
    }
    
    public JsonNode getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(JsonNode countryTo) {
        this.countryTo = countryTo;
    }

    public JsonNode getAvailability() {
        return availability;
    }

    public void setAvailability(JsonNode availability) {
        this.availability = availability;
    }

    /** End of Getters and Setters **/

@Override
  public String toString() {
    return "FlightData {" +
        "\tid=" + id + "\n" +
        "\tflyFrom=" + flyFrom + "\n" +
        "\tflyTo=" + flyTo + "\n" +
        "\tprice=" + price + "\n" +
        "\tcityFrom=" + cityFrom + "\n" +
        "\tcityTo=" + cityTo + "\n" +
        "\tutc_arrival=" + utc_arrival + "\n" +
        "\tutc_departure=" + utc_departure + "\n" +
        "\tcountryFrom['code']=" + countryFrom.get("code") + "\n"+
        "\tcountryTo['code']=" + countryTo.get("code") + "\n"+
        "\tavailability['seats']=" + availability.get("seats") + "\n"+
        "}";
  }

}
