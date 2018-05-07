package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MapDao;

@Service
public class MapService {
	@Autowired
	private MapDao mapDao;
}
