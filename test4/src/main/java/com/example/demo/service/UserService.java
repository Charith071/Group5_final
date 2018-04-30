package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/*import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;*/
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Allusers;
import com.example.demo.entity.User;
import com.example.demo.function.CommonFunction;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	private CommonFunction commonFunction=new CommonFunction();
	
	public String saveuser(HttpServletRequest req,String id){
		
			User user=new User();
			// birth_date format=2018/05/10
			user.setId(Integer.parseInt(id));
			user.setAge(commonFunction.cal_age(req.getParameter("birth_date")));
			user.setName(req.getParameter("name"));
			user.setUsername(req.getParameter("username"));
			user.setPassword(req.getParameter("password"));
			user.setEmail(req.getParameter("email"));
			user.setBirthdate(req.getParameter("birth_date"));
			user.setAddress(req.getParameter("address"));
			user.setGpslocation(req.getParameter("gps_location"));
			user.setPhone_number(Integer.parseInt(req.getParameter("phone_number")));
			user.setJob(req.getParameter("job"));
			user.setGender(req.getParameter("gender"));
			user.setGardiant_phone_number(Integer.parseInt(req.getParameter("gardiant_phone_number")));
			user.setCreate_date(commonFunction.getCurrentdate());
			user.setStress_level(Double.parseDouble(req.getParameter("stress_level")));
			user.setProfile_pic_name(req.getParameter("profile_pic_name"));
			userDao.save(user);
			
			return "User creation is success!!";
		
		
		
		
	}
	
	
	
	
	public boolean update(HttpServletRequest req) {
		if(userDao.existsById(Integer.parseInt(req.getParameter("id")))) {
			User user=userDao.findByIds(Integer.parseInt(req.getParameter("id")));
			if(req.getParameter("email").length()>0) {
				user.setEmail(req.getParameter("email"));
			}
			if(req.getParameter("name").length()>0) {
				user.setName(req.getParameter("name"));
			}
			if(req.getParameter("name").length()>0) {
				
			}
			
			
			userDao.save(user);
			return true;
		}else {
			return false;
		}
	}
	
	
	public User getuserfrom_username_password(HttpServletRequest req){
		User user3=userDao.findByLogin(req.getParameter("username"), req.getParameter("password"));
		return user3;
	}
	
	
	
	//==========================================================
	

	public Optional<User> selectuserByID(int id) {
		 Optional<User> user = userDao.findById(id);
		return user;
	}

	public void dropuserByid(int id) {
		userDao.deleteById(id);
		
	}

	public Object viewallusers() {
				
		return userDao.findAll();
	}

	public Object finduserbyname(String name) {
		
		return userDao.findByName(name);
	}



	public Object finduserbyage(Integer age) {
		
		return userDao.findByAge(age);
	}




	public void delete_by_id(HttpServletRequest req) {
		if(userDao.existsById(Integer.parseInt(req.getParameter("id")))) {
			userDao.deleteById(Integer.parseInt(req.getParameter("id")));
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
