package com.example.demo.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Map;
import com.example.demo.dao.MapDao;

@Service
public class MapService {
	@Autowired
	private MapDao mapDao;
	
	
	//===============update  table(mapping councellers)=========================
	public boolean updateMapDetails(String user_id,String counceller_id) {
	
			Map m1=new Map();
			m1.setUserId(Long.parseLong(user_id));
			m1.setCouncellerId(Long.parseLong(counceller_id));
			mapDao.save(m1);
			return true;
		
	}
	
	//===============update table by instance==================
	public boolean updateTableByInstance(Map m) {
		mapDao.save(m);
		return true;
	}
	
	//===========exist by userid=============
	public boolean existByuser_id(String  user_id) {
		return mapDao.existsByUserId(Long.parseLong(user_id));
	}
	
	
	//=================count number of instance by user_id==================
	public Long numberOfinstance_by_userId(String user_id) {
		return mapDao.countByUserId(Long.parseLong(user_id));
	}
	
	//==============count number of instance by counceller_id===========
	public Long numberOfinstance_by_councellerId(String counceller_id) {
		return mapDao.countByCouncellerId(Long.parseLong("counceller_id"));
	}
	
	//================check_counceller is already exist for particular user=====================
	public boolean is_counceller_already_exist(String user_id,String counceller_id) {
		
		boolean is_counceller_exist=false;
		
		for(Map m:mapDao.findAll()) {
			if(m.getUserId()==Long.parseLong(user_id)) {
				if(m.getCouncellerId()==Long.parseLong(counceller_id)) {
					is_counceller_exist=true;
					break;
				}else {
					is_counceller_exist=false;
				}
			}else {
				is_counceller_exist=false;
			}
		}
		
		return is_counceller_exist;
	}
	

	
	
	
	//===get number of instance=======================
	public Long count_number_of_instance() {
		return mapDao.count();
	}
	
	//============= realtime====================
		public String getLastUptadeTime() {
			return mapDao.getLastUptadeTime();
		}
	
	
	//===============get number of rows======================
	public Long get_number_of_rows() {
		return mapDao.count();
	}
	
	//====================get last recode=======================
	public Map getLastRecorde() {
		return mapDao.getLastRecord();
	}
	
	//==============get instance by counceller id and user id=========================
	public Map getInstanceBy_councellerId_and_userId(String CouncellerId,String userId) {
		return mapDao.getInstanceBycouncellerId_and_userId(Integer.parseInt(CouncellerId),Integer.parseInt(userId));
	}
	
	
}
