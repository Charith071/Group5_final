package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Booking_request;

public interface BookingRequestDao  extends CrudRepository<Booking_request, Long>{

}
