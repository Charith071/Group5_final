package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CouncellerDao;

@Service
public class CouncellerService {
	@Autowired
	private CouncellerDao councellerDao;
}
