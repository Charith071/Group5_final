package com.example.demo.Service;

import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.Counceller;
import com.example.demo.Entity.User;
import com.example.demo.commonFunction.CommnFunction;
import com.example.demo.dao.AlluserDao;
import com.example.demo.dao.CouncellerDao;
import com.example.demo.dao.UserDao;


@Service
public class AllusersService {

	@Autowired
	private AlluserDao alluserDao;
	
	private CommnFunction func=new CommnFunction();
	
	
	
	private CommnFunction cmnFunction=new CommnFunction(); 
	
	//====registraion(updating database)============
	public boolean update_tables(HttpServletRequest req) {
		
		boolean is_duplicate_user=false;
		for(AllUsers users:alluserDao.findAll()) {
			
			if(users.getUsername().equals(req.getParameter("username"))) {
				if(users.getPassword().equals(cmnFunction.string_encript(req.getParameter("password")))) {
					is_duplicate_user=true;
					break;
				}
			}
		}
		
		if(is_duplicate_user) {
			return false;
		}else {
			if(req.getParameter("type").equals("user") || req.getParameter("type").equals("counceller")) {
				AllUsers u1=new AllUsers();
				u1.setAddress(req.getParameter("address"));
				u1.setAge(cmnFunction.cal_age(req.getParameter("birth_date")));
				u1.setBirth_date(req.getParameter("birth_date"));
				u1.setCreate_date(cmnFunction.getCurrentdate());
				u1.setEmail(req.getParameter("email"));
				u1.setGender(req.getParameter("gender"));
				u1.setName(req.getParameter("name"));
				u1.setPassword(cmnFunction.string_encript(req.getParameter("password")));
				u1.setPhone_number(Integer.parseInt(req.getParameter("phone_number")));
				u1.setStatus("enable"); 
				u1.setType(req.getParameter("type"));
				u1.setUsername(req.getParameter("username"));
				alluserDao.save(u1);
				
				return true;
			}else {
				return false;
			}
			
		}
	}
	
	
	//============validate login==================================
	public boolean validate_login(String username,String password) {
		if(alluserDao.existsByPassword(cmnFunction.string_encript(password))) {
			if(alluserDao.existsByUsername(username)) {
				if(getuserfrom_username_password(username, password).getStatus().equals("enable")) {
					return true;
				}else {
					return false;
				}
				
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	//==============return alluser instance using username and password=====
	public AllUsers getuserfrom_username_password(String username,String password){
		AllUsers user3=alluserDao.findByLogin(username, func.string_encript(password));
		return user3;
	}
	
	
	//==========return alluser instance using id=========================
	public AllUsers getuserfrom_id(Integer id) {
		AllUsers u2=alluserDao.findByIds(id);
		return u2;
	}
	
	//============check user is exist by id==============
	public boolean is_user_exist(String id) {
		if(alluserDao.existsById(Long.parseLong(id))) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	
	//*********************************************************************************
	
	//================get all councellers===========================
	public Object get_all_councellers(String status) {
		
		return alluserDao.findByTypeAndStatus("counceller",status);
		//return alluserDao.findAllByType("counceller");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
