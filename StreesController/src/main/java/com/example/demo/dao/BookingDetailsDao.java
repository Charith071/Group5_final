package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.Booking_details;

public interface BookingDetailsDao extends CrudRepository<Booking_details, Long> {

	boolean existsByRequestId(Long Request_id);
	
	@Query(value="select * from booking_details where id= ?1",nativeQuery=true)
	Booking_details findByIds(Integer id);
	
	@Query(value="select * from booking_details where request_id= ?1",nativeQuery=true)
	Booking_details findByReqIds(Integer id);
}
