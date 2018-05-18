package com.example.demo.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Entity.User;
import com.example.demo.dao.UserDao;
import com.example.demo.extra.Gpsjson;
import com.example.demo.extra.Profilepicjson;
import com.example.demo.extra.Signupjson;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	//=========for user registration========================
	public boolean update_table(Signupjson signupjson1,Long id) {
		User u2=new User();
		u2.setGps_location(Double.parseDouble(signupjson1.getGps()));
		u2.setGuadiant_phone_no(signupjson1.getGuadiant_phone_number());
		u2.setId(id);
		u2.setJob(signupjson1.getJob());
		
		u2.setProfile_pic_name(signupjson1.getProfile_pic_name()+id.toString());
		u2.setStress_level(Float.parseFloat("0"));
		userDao.save(u2);
		return true;
	}
	
	
	
	//==========update user stress level=============
	public boolean update_user_stress_level(String id,String level) {
		if(userDao.existsById(Long.parseLong(id))) {
			User u1=userDao.findByIds(Integer.parseInt(id));
			u1.setStress_level(Float.parseFloat(level));
			userDao.save(u1);
			return true;
		}else {
			return false;
		}
			
	} 
	
	//====================update user gps location=============================
	public boolean update_user_gps_location( Gpsjson gpsjson) {
		if(userDao.existsById(Long.parseLong(gpsjson.getId()))) {
			User u2=userDao.findByIds(Integer.parseInt(gpsjson.getId()));
			u2.setGps_location(Double.parseDouble(gpsjson.getGps()));
			userDao.save(u2);
			return true;
		}else {
			return false;
		}
	}
	
	
	
	//==============chnage user profile picture ===============================
	public boolean change_profile(Profilepicjson profilepicjson) {
		if(userDao.existsById(Long.parseLong(profilepicjson.getId()))) {
			User u3=userDao.findByIds(Integer.parseInt(profilepicjson.getId()));
			u3.setProfile_pic_name(profilepicjson.getProfile_pic_name()+profilepicjson.getId());
			userDao.save(u3);
			
			return true;
		}else {
			return false;
		}
	}

	//================return user instance by id===================
	public User getUserby_id( String id) {
		User u4=userDao.findByIds(Integer.parseInt(id));
		return u4;
	}
	
	//=================check user existance==================
	public boolean is_user_exist(String id) {
		return userDao.existsById(Long.parseLong(id));
	}
	
	//==========update edited_user_details==========================
	public boolean update_edited_user_details(User k) {
		userDao.save(k);
		return true;
	}
	
	
	
}
