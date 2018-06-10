package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Changes;
import com.example.demo.dao.ChangesDao;

@Service
public class ChangesService {
	@Autowired
	private ChangesDao changesDao;
	
	//==============update chyanges table============================
	public boolean update_changed_table(Changes data) {
		//System.out.println("testin");
		changesDao.save(data);
		return true;
	}
	
	//===============get changes instance by id==========================
	public Changes get_instance_by_id(String id) {
		return changesDao.findByIds(Integer.parseInt(id));
		
	}
	
	//===============check exist by affected user id=====================
	public boolean is_exist_by_affected_user_id(String affected_id) {
		return changesDao.existsByAffectedUserId(Long.parseLong(affected_id));
	}
	
	//================check exist by id=====================
	public boolean is_exist_by_id(String id) {
		return changesDao.existsById(Long.parseLong(id));
	}
	
	//=========================delete instance by id=====================
	public boolean delete_instance_by_id(String id) {
		changesDao.deleteById(Long.parseLong(id));
		return true; 
	}
	
	
	
	//===============get all_instance by affected usr id and stiill not send===========================
	public Object get_all_insatnce_by_affected_id_and_notsend(String affected_id) {
		return changesDao.getAllInstanceBy_affecctec_id_and_not_send(Long.parseLong(affected_id), "false");
	}
	
	//========get last instance by affected user id===============================================
	public Changes get_last_instance() {
		return changesDao.get_last_instance();
	}
	
	
	
	
	
	
	
	
	
	
	
}
