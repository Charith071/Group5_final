package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.StressLevelHistory;
import com.example.demo.commonFunction.CommnFunction;
import com.example.demo.dao.StressLevelHistoryDao;

@Service
public class StressLevelHistoryService {
	@Autowired
	private StressLevelHistoryDao stressLevelHistoryDao;
	private CommnFunction func=new CommnFunction();
	
	//====================update previous stress level of user===================
	public boolean update_history_strees_level_details(String user_id,String strees_level) {
		StressLevelHistory history=new StressLevelHistory();
		history.setUser_id(Long.parseLong(user_id));
		history.setStress_level(Double.parseDouble(strees_level));
		history.setDate_time(func.getCurrentdate());
		
		stressLevelHistoryDao.save(history);
		
		return true;
	}
	
	
}
