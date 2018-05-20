package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.Booking_request;

public interface BookingRequestDao  extends CrudRepository<Booking_request, Long>{

	
	@Query(value="select * from booking_request where id= ?1",nativeQuery=true)
	Booking_request findByIds(Integer id);
}
