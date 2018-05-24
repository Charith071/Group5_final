package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Booking_details;
import com.example.demo.dao.BookingDetailsDao;

@Service
public class BookingDetailsService {
	@Autowired
	private BookingDetailsDao bookingDetailsDao;
	
	//====================update booking details=====================
	public boolean update_booking_details(Booking_details data) {
		bookingDetailsDao.save(data);
		return true;
	}
	
	//=====================check booking details existance by bookig_details_id===================
	public boolean is_details_exist_by_bookig_details_id(String id) {
		return bookingDetailsDao.existsById(Long.parseLong(id));
	}
	
	//=======================check booking details existance by booking request id====================
	public boolean is_details_exist_by_booking_request_id(String request_id) {
		return bookingDetailsDao.existsByRequestId(Long.parseLong(request_id));
	}
	
	//====================return booking details instance by bookig_details_id======================
	public Booking_details get_insatnce_by_booking_details_id(String id) {
		return bookingDetailsDao.findByIds(Integer.parseInt(id));
	}
	
	//===================return booking details instance by booking_request_id=====================
	public Booking_details get_instance_by_booking_request_id(String booking_request_id) {
		return bookingDetailsDao.findByReqIds(Integer.parseInt(booking_request_id));
	}
	
	//==================delete instance by id=============================
	
	public boolean delete_instance_by_id(String id) {
		bookingDetailsDao.deleteById(Long.parseLong(id));
		return true;
	}
	
	//============= realtime====================
		public String getLastUptadeTime() {
			return bookingDetailsDao.getLastUptadeTime();
		}
		
	//===============get number of rows======================
	public Long get_number_of_rows() {
		return bookingDetailsDao.count();
	}
	
	//====================get last recode=======================
	public Booking_details getLastRecorde() {
		return bookingDetailsDao.getLastRecord();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
