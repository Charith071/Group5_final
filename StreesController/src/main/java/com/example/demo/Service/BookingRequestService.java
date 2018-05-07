package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookingRequestDao;

@Service
public class BookingRequestService {
	@Autowired
	private BookingRequestDao bookingRequestDao;
}
