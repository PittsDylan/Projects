package com.snhu.Milestoneapp.Models;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * List of flight itineraries
 * 
 * Purpose: get all flight itinerarie
 */
public class SearchResponse {

  private ArrayList<FlightData> data; // list of objects representing flight iterneraries

  //Constructor
  public SearchResponse() {
    this.data = new ArrayList<>();
  }

  //Getter and Setter
  public void setData(ArrayList<FlightData> data) {
    this.data = data;
  }

  public ArrayList<FlightData> getData() {
    return data;
  }

  @Override
  public String toString() {
    return "SearchResponse {" +
        "\tdata=" + data.toString() + "\n" +
        "}";
  }
}
