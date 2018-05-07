package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AlluserDao;

@Service
public class AllusersService {

	@Autowired
	private AlluserDao alluserDao;
	
	
}
