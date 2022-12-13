package com.snhu.Milestoneapp.Services;

import java.util.List;
import java.util.Optional;

import com.snhu.Milestoneapp.Models.*;
import com.snhu.Milestoneapp.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * business/service layer for booking flights
 * 
 * Acts as an inbetween from Controller to model and database.
 * Performs the logic on data mapped on the model and database.
 * 
 * Purpose: methods for what the service layer does
 */
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private bookingrepository repo;

    //get all flights booked from book data model
    @Override
    public List<book> findAllBookings() {
        return repo.findAll();
    }

    //find flight booked from book data model using id field [not in use]
    @Override
    public book findBookingById(long id) {
        Optional<book> optional = repo.findById(id);
        book b = null;

        if(optional.isPresent()){
            b = optional.get();
        }
        else {
            throw new RuntimeException("booking not found for id :: " + id);
        }
        return b;
    }

    //save flight data to the booking database
    @Override
    public void saveBooking(book b){
         this.repo.save(b);   
    }
}
