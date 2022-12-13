package com.snhu.Milestoneapp.Services;

import java.util.List;

import com.snhu.Milestoneapp.Models.book;

/**
 * business/service layer for booking flights
 * 
 * Acts as an inbetween from Controller to model and database.
 * Performs the logic on data mapped on the model and database.
 * 
 * Purpose: interface for booking flights connects bussiness layer to controller
 */
public interface BookingService {
    //Purpose: gets all flights from database
    List <book> findAllBookings();
    //Purpose: inserts flight datab into database
    void saveBooking(book b);
    //Purpose: get flight by id [not in use]
    book findBookingById(long id);
}