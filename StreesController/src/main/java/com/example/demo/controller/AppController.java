package com.example.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.Counceller;
import com.example.demo.Entity.Map;
import com.example.demo.Entity.User;
import com.example.demo.Service.AdminNotificationService;
import com.example.demo.Service.AllusersService;
import com.example.demo.Service.BookingDetailsService;
import com.example.demo.Service.BookingRequestService;
import com.example.demo.Service.CouncellerService;
import com.example.demo.Service.MapService;
import com.example.demo.Service.MessagesService;
import com.example.demo.Service.MusicTrackService;
import com.example.demo.Service.StressLevelHistoryService;
import com.example.demo.Service.TipsService;
import com.example.demo.Service.UserService;
import com.example.demo.dao.MapDao;
import com.example.demo.extra.Chatjson;
import com.example.demo.extra.Getleveljson;
import com.example.demo.extra.Gpsjson;
import com.example.demo.extra.Leveljson;
import com.example.demo.extra.Loginjson;
import com.example.demo.extra.Mappingjson;
import com.example.demo.extra.Profilepicjson;
import com.example.demo.extra.Signupjson;

@RestController
@RequestMapping("/api")
public class AppController {
	
	@Autowired
	private AllusersService allusersService;
	@Autowired
	private AdminNotificationService adminNotificationService;
	@Autowired
	private BookingDetailsService bookingDetailsService;
	@Autowired
	private BookingRequestService bookingRequestService;
	@Autowired
	private CouncellerService councellerService;
	@Autowired
	private MapService mapService;
	@Autowired
	private MessagesService messagesService;
	@Autowired
	private MusicTrackService musicTrackService;
	@Autowired
	private StressLevelHistoryService stressLevelHistoryService;
	@Autowired
	private TipsService tipsService;
	@Autowired
	private UserService userService;
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping("")
	public ResponseEntity<?> home(){
		return ResponseEntity.ok("Home");
	}
	
	
	//=========registration====================================
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody Signupjson signupjson){
		try {
			if(allusersService.update_tables(signupjson)) {
				AllUsers alluser=allusersService.getuserfrom_username_password(signupjson.getUsername(),signupjson.getPassword());
				if(alluser.getType().equals("user")) {
					userService.update_table(signupjson,alluser.getId());
					return ResponseEntity.ok("user registration is succcess!!");
				}else if(alluser.getType().equals("counceller")) {
					councellerService.update_table(signupjson,alluser.getId());
					return ResponseEntity.ok("Counceller registration is succcess!!");
				}else {
					return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
				}
				//return ResponseEntity.ok("Counceller registration is succcess!!");
			}else {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("registration fail...change username or password");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
		
	}
	
	//=============login===========================
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Loginjson lj){
		
		try {
			if(allusersService.validate_login(lj.getUsername(),lj.getPassword())) {
				AllUsers u3=allusersService.getuserfrom_username_password(lj.getUsername(),lj.getPassword());
				return ResponseEntity.ok(u3);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid username or password");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
	}
	
	
	//==============update stresslevel of user=======================
	@RequestMapping(value="/level",method=RequestMethod.POST)
	public ResponseEntity<?> level_update(@RequestBody Leveljson leveljson){
		try {
			if(userService.update_user_stress_level(leveljson.getId(),leveljson.getLevel())) {
				return ResponseEntity.ok("success!!");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail!! cannot find user");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
	}
	
	
	//==================update user current gps location=========================
	@RequestMapping(value="/user/gps",method=RequestMethod.POST)
	public ResponseEntity<?> update_user_gps_location( @RequestBody Gpsjson gpsjson){
		try {
			if(userService.update_user_gps_location(gpsjson)) {
				return ResponseEntity.ok("gps update success!!");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot find user");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
	}
	
	//===================update counceller gps location===================
	@RequestMapping(value="/counceller/gps",method=RequestMethod.POST)
	public ResponseEntity<?> update_counceller_gps( @RequestBody Gpsjson gpsjson1){
		try {
			if(councellerService.update_gps_location(gpsjson1)) {
				return ResponseEntity.ok("update success!!");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail!! cannot find Counceller");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
	}
	
	//================change profile picture=======================
	@RequestMapping(value="/ChangeProfilePicture",method=RequestMethod.POST)
	public ResponseEntity<?> changeProfilePicture(@RequestBody Profilepicjson profilepicjson){
		
		try {
			if(allusersService.is_user_exist(profilepicjson.getId())) {
				AllUsers au1=allusersService.getuserfrom_id(Integer.parseInt(profilepicjson.getId()));
				if(au1.getType().equals("user")) {
					if(userService.change_profile(profilepicjson)) {
						return ResponseEntity.ok("update success!!");
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail!! , Cannot find user");
					}
				}else if(au1.getType().equals("counceller")) {
					if(councellerService.change_profile(profilepicjson)) {
						return ResponseEntity.ok("update successs!!");
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail!! , Cannot find Counceller");
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail!! , Cannot find");
				}
				
			}else {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user is not exist!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
	}
	
	
	//=====================get user stress level=============================
	@RequestMapping(value="/getLevel",method=RequestMethod.POST)
	public ResponseEntity<?> getStressLevel(@RequestBody Getleveljson getleveljson){
		
		try {
			if(userService.is_user_exist(getleveljson.getId())) {
				User u1=userService.getUserby_id(getleveljson.getId());
				return ResponseEntity.ok(u1.getStress_level());
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot find user");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
	}
	
	
	
	//=================map councellers by user======================
		@RequestMapping(value="/user/mapCounceller",method=RequestMethod.POST)
		public ResponseEntity<?> mapcounceller(@RequestBody Mappingjson mappingjson){
			try {
			
				if(allusersService.getuserfrom_id(Integer.parseInt(mappingjson.getUser_id())).getType().equals("user") && allusersService.getuserfrom_id(Integer.parseInt(mappingjson.getCounceller_id())).getType().equals("counceller")) {
					
					if(mapService.numberOfinstance_by_userId(mappingjson.getUser_id())==1 || mapService.numberOfinstance_by_userId(mappingjson.getUser_id())==0) {
						if(mapService.is_counceller_already_exist(mappingjson.getUser_id(), mappingjson.getCounceller_id())) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("alredy has a this counceller");
						}else {
							if(mapService.updateMapDetails(mappingjson.getUser_id(),mappingjson.getCounceller_id())) {
								return ResponseEntity.ok("counceller mapping  success");
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mapping fail");
							}
						}
						
						
					
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("u have already two councellers");
					}
					
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user or counceller does not exist");
				}
				
				
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
			}
		}
	
	
		
		
		
		//==================online chat with  counceller vs users==============================
		@RequestMapping(value="/chat",method=RequestMethod.POST)
		public ResponseEntity<?> chat(@RequestBody Chatjson chatjson){
			try {
				
				if(allusersService.is_user_exist(chatjson.getSender_id()) && allusersService.is_user_exist(chatjson.getReceiver_id()) ) {
					AllUsers sender=allusersService.getuserfrom_id(Integer.parseInt(chatjson.getSender_id()));
					AllUsers receiver=allusersService.getuserfrom_id(Integer.parseInt(chatjson.getReceiver_id()));
					if(sender.getStatus().equals("enable") && receiver.getStatus().equals("enable")) {
						if(sender.getType().equals("user")) {
							//===========from user to counceller=============
							if(mapService.is_counceller_already_exist(chatjson.getSender_id(), chatjson.getReceiver_id())) {
								if(messagesService.update_msg_details(chatjson)) {
									return ResponseEntity.ok("msg details update successs!!");
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot update table!");
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please map the counceller and re try");
							}
						}else if(sender.getType().equals("counceller")) {
							//=====from counceler to user============
							if(mapService.is_counceller_already_exist(chatjson.getReceiver_id(), chatjson.getSender_id())) {
								if(messagesService.update_msg_details(chatjson)) {
									return ResponseEntity.ok("msg details update successs!!");
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot update table!");
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please map the counceller and re try");
							}
							//return ResponseEntity.ok("jshajdh");
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Access Denied!!");
						}
						
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a valid user or Counceller");
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user or counceller does not exist!!");
				}
			
			
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid user Inputs");
			}
		
		}
			
		
		
		
		
		
		
		
		
		
		
//*****************************************************************************************	***********
	
	
	
	//=================get_all_councellerIds========================================
	@RequestMapping(value="user/getAllCouncellers",method=RequestMethod.POST)
	public ResponseEntity<?> getAllCouncellers(){
		
		return ResponseEntity.ok(allusersService.get_all_councellers("enable"));
	}
	
	
//===================remove exist maped counceller by user=======================================
	/*@RequestMapping(value="/user/removeCounceller",method=RequestMethod.POST)
	public ResponseEntity<?> removeExistCouncellerByUser(HttpServletRequest req){
		
	}*/
		
	
	
	/*@RequestMapping(value="/test",method=RequestMethod.POST)
	public ResponseEntity<?> testing(){
		List<String> list = new ArrayList<>();
	      list.add("One");
	      list.add("Two");
	      list.add("Three");
		 
		 return ResponseEntity.ok(list);
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
}
