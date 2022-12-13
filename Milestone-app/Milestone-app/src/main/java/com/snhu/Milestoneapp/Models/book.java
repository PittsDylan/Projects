package com.snhu.Milestoneapp.Models;

import javax.persistence.*;

/**
 * booking model
 * 
 * Purpose: translate book model with 
 * booking table from booking database.
 */
@Entity
@Table(name = "booking")
public class book {

    //id field auto generates
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int booking_id;

    //username
    @Column(name = "username")
    private String username;

    //departure airport
    @Column(name = "from_Airport")
    private String fromAirport;
    
    //arrival airport
    @Column(name = "to_Airport")
    private String toAirport;

    //departure city
    @Column(name = "from_City")
    private String fromCity;
    
    //arrival city
    @Column(name = "to_City")
    private String toCity;
    
    //departure country
    @Column(name = "from_Country")
    private String fromCountry;
    
    //arrival country
    @Column(name = "to_Country")
    private String toCountry;
    
    //departure time
    @Column(name = "from_utc")
    private String from_utc;
    
    //arrival time
    @Column(name = "to_utc")
    private String to_utc;

    //available seats
    @Column(name = "seats")
    private String seats;
    
    //cost of ticket
    @Column(name = "price")
    private String price;

    //getters and setters
    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getToCountry() {
        return toCountry;
    }

    public void setToCountry(String toCountry) {
        this.toCountry = toCountry;
    }

    public String getFrom_utc() {
        return from_utc;
    }

    public void setFrom_utc(String from_utc) {
        this.from_utc = from_utc;
    }

    public String getTo_utc() {
        return to_utc;
    }

    public void setTo_utc(String to_utc) {
        this.to_utc = to_utc;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
