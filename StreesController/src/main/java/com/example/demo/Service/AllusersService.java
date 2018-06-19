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
import com.example.demo.extra.Signupjson;


@Service
public class AllusersService {

	@Autowired
	private AlluserDao alluserDao;
	
	private CommnFunction func=new CommnFunction();
	
	
	
	private CommnFunction cmnFunction=new CommnFunction(); 
	
	//====registraion(updating database)============
	public boolean update_tables(Signupjson signupjson) {
		
		boolean is_duplicate_user=false;
		for(AllUsers users:alluserDao.findAll()) {
			
			if(users.getUsername().equals(signupjson.getUsername())) {
				is_duplicate_user=true;
				
				if(users.getPassword().equals(cmnFunction.string_encript(signupjson.getPassword()))) {
					is_duplicate_user=true;
					break;
				}
				if(is_duplicate_user) {
					break;
				}
			}
		}
		
		if(is_duplicate_user) {
			return false;
		}else {
			if(signupjson.getType().equals("user") || signupjson.getType().equals("counceller")) {
				AllUsers u1=new AllUsers();
				u1.setAddress(signupjson.getAddress());
				u1.setAge(cmnFunction.cal_age(signupjson.getBirth_date()));
				u1.setBirth_date(signupjson.getBirth_date());
				u1.setCreate_date(cmnFunction.getCurrentdate());
				u1.setEmail(signupjson.getEmail());
				u1.setGender(signupjson.getGender());
				u1.setName(signupjson.getName());
				u1.setPassword(cmnFunction.string_encript(signupjson.getPassword()));
				u1.setPhone_number(signupjson.getPhone_number());
				if(signupjson.getType().equals("user")) {
					u1.setStatus("enable"); 
					u1.setLastLogin(cmnFunction.getCurrentdateTime());
				}else {
					u1.setStatus("disable"); 
				}
				
				u1.setType(signupjson.getType());
				u1.setUsername(signupjson.getUsername());
				alluserDao.save(u1);
				
				return true;
			}else {
				return false;
			}
			
		}
	}
	
	
	//============validate login==================================
	public boolean validate_login(String username,String password) {
		boolean validity=false;
		for(AllUsers a:alluserDao.findAll()) {
			if(a.getUsername().equals(username)) {
				if(a.getPassword().equals(cmnFunction.string_encript(password))) {
					AllUsers b=getuserfrom_username_password(username, password);
					if(b.getType().equals("admin")) {
						validity=true;
						break;
						
					}else {
						if(b.getType().equals("counceller") || b.getType().equals("user")) {
							validity=true;
							break;
						}else {
							validity=false;
						}
					}
				}else {
					validity=false;
				}
			}else {
				validity=false;
			}
		}
		
		return validity;

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
	
	
	
	
	//======================update any alluser instance===============
	public boolean updateAlluserInstance(AllUsers user) {
		alluserDao.save(user);
		return true;
	}
	
	
	//================check duplicate user============================
	public boolean check_is_duplicate_user(String username,String password) {
		boolean is_duplicate_user=false;
		for(AllUsers users:alluserDao.findAll()) {
			
			if(users.getUsername().equals(username)) {
				is_duplicate_user=true;
				if(users.getPassword().equals(cmnFunction.string_encript(password))) {
					is_duplicate_user=true;
					break;
				}
				if(is_duplicate_user) {
					break;
				}
			}
		}
		return is_duplicate_user;
	}
	
	
	
	//=============update account edited details===================
	public boolean update_Edited_account_detals(AllUsers aa) {
		alluserDao.save(aa);
		return true;
	}
	
	
	
	
	//============= realtime====================
		public String getLastUptadeTime() {
			return alluserDao.getLastUptadeTime();
		}
	
		//===============get number of rows======================
		public Long get_number_of_rows() {
			return alluserDao.count();
		}
	
	
		//====================get last recode=======================
		public AllUsers getLastRecorde() {
			return alluserDao.getLastRecord();
		}
		
	
	//*********************************************************************************
	
	//================get all councellers===========================
	public Object get_all_councellers(String status) {
		
		return alluserDao.findByTypeAndStatus("counceller",status);
		//return alluserDao.findAllByType("counceller");
	}
	
	//============get instance from keycloak id=============================
	public AllUsers getInstanceFrom_keycloakId(String keycloakId) {
		return alluserDao.findByKeycloakId(keycloakId);
	}
	
	//==============check existby keycloak id=====================
	public boolean is_exisit_by_keycloakId(String keycloakId) {
		return alluserDao.existsByKeycloakId(keycloakId);
	}
	
	
	
	
	
	
	
	
	
	
	
}
