package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AllusersDao;
import com.example.demo.entity.Allusers;
import com.example.demo.function.CommonFunction;

@Service
public class AlluserServices {
	@Autowired
	private AllusersDao alluserdao;
	
	private CommonFunction commonFunction=new CommonFunction();
	
	public boolean saveuser(HttpServletRequest req) {
		boolean is_duplicate=false;
		for(Allusers users:alluserdao.findAll()) {
			if(alluserdao.existsByUsername(req.getParameter("username"))) {
				if(alluserdao.existsByPassword(req.getParameter("password"))) {
					is_duplicate=true;
				}
			}
		}
		
		if(is_duplicate) {
			return false;
		}else {
			Allusers user2=new Allusers();
			user2.setName(req.getParameter("name"));
			user2.setPassword(req.getParameter("password"));
			if(req.getParameter("type").equals("user") || req.getParameter("type").equals("consulter")) {
				user2.setType(req.getParameter("type"));
				user2.setUsername(req.getParameter("username"));
				alluserdao.save(user2);
				return true;
			}else {
				return false;
			}
			
			
		}
	
	}
	
	public boolean signin(HttpServletRequest req) {
	
		boolean status=false;
		for(Allusers alluser1: alluserdao.findAll()) {
			if(alluserdao.existsByPassword(req.getParameter("password"))) {
				if(alluserdao.existsByUsername(req.getParameter("username"))) {
					status=true;
				}
			}
		}
		
		return status;
	}
	
	public boolean delete_user_by_id(HttpServletRequest req) {
		if(alluserdao.existsById(Integer.parseInt(req.getParameter("id")))){
			alluserdao.deleteById(Integer.parseInt(req.getParameter("id")));
			return true;
		}else {
			return false;
		}
	}
	
	
	public Integer getUserId(HttpServletRequest req) {
		Allusers user1=alluserdao.findByLogin(req.getParameter("username"), req.getParameter("password"));
		return user1.getId();
	}
	
	public Allusers getuserfrom_username_password(HttpServletRequest req){
		Allusers user3=alluserdao.findByLogin(req.getParameter("username"), req.getParameter("password"));
		return user3;
	}
	
	public Object viewallusers() {
		
		return alluserdao.findAll();
	}

}
