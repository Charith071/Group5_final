package com.example.demo.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	//=========for user registration========================
	public boolean update_table(HttpServletRequest req,Long id) {
		User u2=new User();
		
		u2.setGps_location(Double.parseDouble(req.getParameter("gps")));
		u2.setGuadiant_phone_no(Integer.parseInt(req.getParameter("guadiant_phone_number")));
		u2.setId(id);
		u2.setJob(req.getParameter("job"));
		
		u2.setProfile_pic_name(req.getParameter("profile_pic_name")+id.toString());
		u2.setStress_level(Float.parseFloat("0"));
		userDao.save(u2);
		return true;
	}
	
	
	
	//==========update user stress level=============
	public boolean update_user_stress_level(HttpServletRequest req) {
		if(userDao.existsById(Long.parseLong(req.getParameter("id")))) {
			User u1=userDao.findByIds(Integer.parseInt(req.getParameter("id")));
			u1.setStress_level(Float.parseFloat(req.getParameter("level")));
			userDao.save(u1);
			return true;
		}else {
			return false;
		}
			
	} 
	
	//====================update user gps location=============================
	public boolean update_user_gps_location(HttpServletRequest req) {
		if(userDao.existsById(Long.parseLong(req.getParameter("id")))) {
			User u2=userDao.findByIds(Integer.parseInt(req.getParameter("id")));
			u2.setGps_location(Double.parseDouble(req.getParameter("gps")));
			userDao.save(u2);
			return true;
		}else {
			return false;
		}
	}
	
	
	
	//==============chnage user profile picture ===============================
	public boolean change_profile(HttpServletRequest req) {
		if(userDao.existsById(Long.parseLong(req.getParameter("id")))) {
			User u3=userDao.findByIds(Integer.parseInt(req.getParameter("id")));
			u3.setProfile_pic_name(req.getParameter("profile_pic_name")+req.getParameter("id"));
			userDao.save(u3);
			
			return true;
		}else {
			return false;
		}
	}

	//================return user instance by id===================
	public User getUserby_id(HttpServletRequest req) {
		User u4=userDao.findByIds(Integer.parseInt(req.getParameter("id")));
		return u4;
	}
	
	//=================check user existance==================
	public boolean is_user_exist(HttpServletRequest req) {
		return userDao.existsById(Long.parseLong(req.getParameter("id")));
	}
	
	
	
	
	
}
