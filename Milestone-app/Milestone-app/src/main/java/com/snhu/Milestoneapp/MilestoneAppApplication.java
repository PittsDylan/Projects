package com.snhu.Milestoneapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Flight Service Web app
 * 
 * Give users access to a flight service that
 * contains flight search and booking.
 */
@SpringBootApplication
@RestController
public class MilestoneAppApplication {
  
	public static void main(String[] args) {
		SpringApplication.run(MilestoneAppApplication.class, args);
	}
}
