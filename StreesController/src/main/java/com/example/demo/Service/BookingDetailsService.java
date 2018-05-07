package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookingDetailsDao;

@Service
public class BookingDetailsService {
	@Autowired
	private BookingDetailsDao bookingDetailsDao;
}
