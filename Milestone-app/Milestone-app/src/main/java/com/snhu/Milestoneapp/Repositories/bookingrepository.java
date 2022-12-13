package com.snhu.Milestoneapp.Repositories;

import com.snhu.Milestoneapp.Models.book;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * interface for booking flights connects bussiness layer to model
 */
public interface bookingrepository extends JpaRepository<book, Long> {}
