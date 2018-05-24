package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Tips;
import com.example.demo.dao.TipsDao;

@Service
public class TipsService {
	@Autowired
	private TipsDao tipsDao;
	
	//=================update tips===================
	public boolean update_table(Tips data) {
		tipsDao.save(data);
		return true;
	}
	//=================delete by tip_id=====================
	public boolean delete_by_tip_id(String tip_id) {
		tipsDao.deleteById(Long.parseLong(tip_id));
		return true;
	}
	
	//==================exist by tip id==========================
	public boolean is_tip_exist_by_tip_id(String tip_id) {
		return tipsDao.existsById(Long.parseLong(tip_id));
	}
	
	
	
	
	
	
	//============= realtime====================
	
	public String getLastUptadeTime() {
		
		return tipsDao.getLastUptadeTime();
	}
	
	//===============get number of rows======================
	public Long get_number_of_rows() {
		return tipsDao.count();
	}
	
	//====================get last recode=======================
	public Tips getLastRecorde() {
		return tipsDao.getLastRecord();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
