package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AdminNotification;
import com.example.demo.dao.AdminNotificationDao;

@Service
public class AdminNotificationService {
	@Autowired
	private AdminNotificationDao adminNotificationDao;
	
	//============update admin notification======================
	public boolean update_admin_notifcation(AdminNotification notice) {
		adminNotificationDao.save(notice);
		return true;	
	}
	
	//===============get instance by id=============================
	public AdminNotification get_instace_by_id(String id) {
		return adminNotificationDao.findByIds(Integer.parseInt(id));
	}
	
	//=============check notice is avalable by id================
	public boolean is_notice_exisit(String id) {
		
		return adminNotificationDao.existsById(Long.parseLong(id));
	}
	
	//===========delete by id=========================
	public boolean delete_by_id(String id) {
		adminNotificationDao.deleteById(Long.parseLong(id));
		return true;
	}
	
	
	//============= realtime====================
	public String getLastUptadeTime() {
		return adminNotificationDao.getLastUptadeTime();
	}
	
	//===============get number of rows======================
	public Long get_number_of_rows() {
		return adminNotificationDao.count();
	}
	
	//====================get last recode=======================
	public AdminNotification getLastRecorde() {
		return adminNotificationDao.getLastRecord();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
