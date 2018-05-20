package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Booking_request;
import com.example.demo.dao.BookingRequestDao;

@Service
public class BookingRequestService {
	@Autowired
	private BookingRequestDao bookingRequestDao;
	
	//===============update booking request======================
	public boolean update_booking_request( Booking_request req) {
		bookingRequestDao.save(req);
		return true;
	}
	
	//============check request is exist by id================
	public boolean is_request_exist_by_id(String id) {
		return bookingRequestDao.existsById(Long.parseLong(id));
	}
	
	//==============return request instance by id======================
	public Booking_request get_instance_by_id(String id) {
		return bookingRequestDao.findByIds(Integer.parseInt(id));
	}
	
	//===================delete request by id=====================
	public boolean delete_request_by_id(String id) {
		 bookingRequestDao.deleteById(Long.parseLong(id));
		 return true;
	}
	
	
	
	
	
	
	
	
	
}
