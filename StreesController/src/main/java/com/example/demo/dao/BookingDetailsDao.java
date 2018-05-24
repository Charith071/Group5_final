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
	
	
	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'booking_details'",nativeQuery=true)
	String getLastUptadeTime();
	
	
	@Query(value="select * from booking_details order by id desc limit 1",nativeQuery=true)
	 Booking_details getLastRecord();
}
