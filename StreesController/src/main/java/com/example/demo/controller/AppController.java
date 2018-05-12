package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.Counceller;
import com.example.demo.Entity.User;
import com.example.demo.Service.AdminNotificationService;
import com.example.demo.Service.AllusersService;
import com.example.demo.Service.BookingDetailsService;
import com.example.demo.Service.BookingRequestService;
import com.example.demo.Service.CouncellerService;
import com.example.demo.Service.MapService;
import com.example.demo.Service.MessageService;
import com.example.demo.Service.MusicTrackService;
import com.example.demo.Service.StressLevelHistoryService;
import com.example.demo.Service.TipsService;
import com.example.demo.Service.UserService;

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
	private MessageService messageService;
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
	public ResponseEntity<?> registration(HttpServletRequest req){
		try {
			if(allusersService.update_tables(req)) {
				AllUsers alluser=allusersService.getuserfrom_username_password(req);
				
				if(alluser.getType().equals("user")) {
					userService.update_table(req, alluser.getId());
					return ResponseEntity.ok("user registration is succcess!!");
				}else if(alluser.getType().equals("counceller")) {
					councellerService.update_table(req,alluser.getId());
					return ResponseEntity.ok("Counceller registration is succcess!!");
				}else {
					return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
				}
			//	return ResponseEntity.ok("Counceller registration is succcess!!");
			}else {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("registration fail...change username or password");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
		
	}
	
	//=============login===========================
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public ResponseEntity<?> login(HttpServletRequest req){
		if(allusersService.validate_login(req)) {
			AllUsers u3=allusersService.getuserfrom_username_password(req);
			return ResponseEntity.ok(u3);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid username or password");
		}
	}
	
	
	//==============update stresslevel of user=======================
	@RequestMapping(value="/level",method=RequestMethod.GET)
	public ResponseEntity<?> level_update(HttpServletRequest req){
		try {
			if(userService.update_user_stress_level(req)) {
				return ResponseEntity.ok("success!!");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail!! cannot find user");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
	}
	
	
	//==================update user current gps location=========================
	@GetMapping(value="/user/gps")
	public ResponseEntity<?> update_user_gps_location(HttpServletRequest req){
		try {
			if(userService.update_user_gps_location(req)) {
				return ResponseEntity.ok("gps update success!!");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot find user");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
	}
	
	//===================update counceller gps location===================
	@GetMapping(value="/counceller/gps")
	public ResponseEntity<?> update_counceller_gps(HttpServletRequest req){
		try {
			if(councellerService.update_gps_location(req)) {
				return ResponseEntity.ok("update success!!");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail!! cannot find Counceller");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
	}
	
	//================change profile picture=======================
	@GetMapping(value="/ChangeProfilePicture")
	public ResponseEntity<?> changeProfilePicture(HttpServletRequest req){
		
		try {
			if(allusersService.is_user_exist(req)) {
				AllUsers au1=allusersService.getuserfrom_id(Integer.parseInt(req.getParameter("id")));
				if(au1.getType().equals("user")) {
					if(userService.change_profile(req)) {
						return ResponseEntity.ok("update success!!");
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail!! , Cannot find user");
					}
				}else if(au1.getType().equals("counceller")) {
					if(councellerService.change_profile(req)) {
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
	@GetMapping(value="/getLevel")
	public ResponseEntity<?> getStressLevel(HttpServletRequest req){
		
		try {
			if(userService.is_user_exist(req)) {
				User u1=userService.getUserby_id(req);
				return ResponseEntity.ok(u1.getStress_level());
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot find user");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
	}
	
	
	//*****************************************************************************************	
	
	
	
	//=================get_all_councellerIds========================================
	@GetMapping(value="user/getAllCouncellers")
	public ResponseEntity<?> getAllCouncellers(){
		
		return ResponseEntity.ok(allusersService.get_all_councellers());
	}
	
	
//=================counceller mapping(by user)================================
	@GetMapping(value="user/mapCounceller")
	public ResponseEntity<?> mapCounceller(HttpServletRequest req){
		/*if(mapService.existByuser_id(req)) {
			return ResponseEntity.ok(mapService.numberOfinstance_by_userId(req));
			
		}else {
			return ResponseEntity.ok("cannot find");
		}*/
		
		
		return ResponseEntity.ok(mapService.numberOfinstance_by_userId(req));
	}
		
	//=================get counceller======================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
