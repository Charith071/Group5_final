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
		history.setUserId(Long.parseLong(user_id));
		history.setStressLevel(Double.parseDouble(strees_level));
		history.setDateTime(func.getCurrentdate());
		
		stressLevelHistoryDao.save(history);
		
		return true;
	}
	
	
	//============= realtime====================
		public String getLastUptadeTime() {
			return stressLevelHistoryDao.getLastUptadeTime();
		}
	
	//===============get number of rows======================
	public Long get_number_of_rows() {
		return stressLevelHistoryDao.count();
	}
	
	//====================get last recode=======================
	public StressLevelHistory getLastRecorde() {
		return stressLevelHistoryDao.getLastRecord();
	}
	
	//=============get instances by userId=========================
	public Object getInstancesByUserId(String UserId){
		return stressLevelHistoryDao.getRegdcordsByUserId(Long.parseLong(UserId));
	}
	
	//===============check is exist by userId======================
	public boolean Is_exist_by_userId(String userId) {
		return stressLevelHistoryDao.existsByUserId(Long.parseLong(userId));
	}
	
	
}
