package com.example.demo.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Allusers;
import com.example.demo.entity.Consulter;
import com.example.demo.entity.User;
import com.example.demo.service.AlluserServices;
import com.example.demo.service.ConsulterService;
//import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class AppController {
 
	@Autowired
	private UserService userService;
	@Autowired
	private AlluserServices alluserServices;
	@Autowired
	private ConsulterService consulterService;
	
	@RequestMapping("")
	public ResponseEntity<?> m1(){
		return ResponseEntity.ok("home page");
	}
	
	
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public ResponseEntity<?> login(HttpServletRequest req){
		boolean status= alluserServices.signin(req);
		if(status){
			Allusers au1=alluserServices.getuserfrom_username_password(req);
			if(au1.getType().equals("user")) {
				User u1=userService.getuserfrom_username_password(req);
				return ResponseEntity.ok(u1);
			}else if(au1.getType().equals("consulter")) {
				Consulter c1=consulterService.getuserfrom_username_password(req);
				return ResponseEntity.ok(c1);
			}else if(au1.getType().equals("admin")) {
				
				return ResponseEntity.ok(au1);
			}else {
				return ResponseEntity.ok("loging fail");
			}
			
		
			
		}else {
			return  ResponseEntity.ok("login fail");
		}
		
		
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ResponseEntity<?> newusercreation(HttpServletRequest req){
		if(alluserServices.saveuser(req)) {
			Allusers u1=alluserServices.getuserfrom_username_password(req);
			if(u1.getType().equals("user")) {
				return ResponseEntity.ok(userService.saveuser(req,u1.getId().toString()));
			}else if(u1.getType().equals("consulter")) {
				return ResponseEntity.ok(consulterService.saveuser(req, u1.getId().toString()));
			}else if(u1.getType().equals("admin")){
				return ResponseEntity.ok("developping admin");
			}else {
				return ResponseEntity.ok("error");
			}
			
		}else {
			return ResponseEntity.ok("alredy has a account!! change username or password");
		}
		
		
	}

	@RequestMapping(value="/dropuser")
	public ResponseEntity<?> delete_user_by_id(HttpServletRequest req){
		
		if(alluserServices.delete_user_by_id(req)) {
			userService.delete_by_id(req);
			consulterService.delete_by_id(req);
			
			return ResponseEntity.ok("user is deleted");
		}else {
			return ResponseEntity.ok("user is not exist!!");
		}
		
	}
	
	
	
	
	@RequestMapping(value="/viewall",method=RequestMethod.POST)
	public ResponseEntity<?> get_all_users_from_Allusers_table(){
		return ResponseEntity.ok(alluserServices.viewallusers());
		
	}
	
	@RequestMapping(value="/findbyname",method=RequestMethod.POST)
	public ResponseEntity<?> m6(HttpServletRequest req){
		String name=req.getParameter("name");
		return ResponseEntity.ok(userService.finduserbyname(name));
		
	}
	
	@RequestMapping(value="/findbyage",method=RequestMethod.POST)
	public ResponseEntity<?> m7(HttpServletRequest req){
		Integer age=Integer.parseInt(req.getParameter("age"));
		return ResponseEntity.ok(userService.finduserbyage(age));
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<?> m8(HttpServletRequest req){
		boolean status=userService.update(req);
		if(status) {
			return ResponseEntity.ok("Update success");
		}else {
			return ResponseEntity.ok("update faild");
		}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
