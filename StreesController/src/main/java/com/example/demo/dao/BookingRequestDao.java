package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.Entity.Booking_request;

public interface BookingRequestDao  extends CrudRepository<Booking_request, Long>{

	
	@Query(value="select * from booking_request where id= ?1",nativeQuery=true)
	Booking_request findByIds(Integer id);
	
	@Query(value="SELECT UPDATE_TIME FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'mind' AND TABLE_NAME = 'booking_request'",nativeQuery=true)
	String getLastUptadeTime();
	
	@Query(value="select * from booking_request order by id desc limit 1",nativeQuery=true)
	 Booking_request getLastRecord();
	
	@Query(value="select * from booking_request where counceller_id=?1 and user_id=?2 and date_time=?3 and status='true'",nativeQuery=true)
	 Booking_request getInstanceBy_councellerId_user_id_status_date(Integer counceller_Id,Integer user_Id,String date);
}
