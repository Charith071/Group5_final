package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StressLevelHistoryDao;

@Service
public class StressLevelHistoryService {
	@Autowired
	private StressLevelHistoryDao stressLevelHistoryDao;
}
