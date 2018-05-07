package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Booking_details;

public interface BookingDetailsDao extends CrudRepository<Booking_details, Long> {

}
