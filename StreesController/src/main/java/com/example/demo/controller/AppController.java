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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.Counceller;
import com.example.demo.Entity.Map;
import com.example.demo.Entity.MusicTrack;
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
import com.example.demo.extra.AccountSettingjson;
import com.example.demo.extra.AddTrackjson;
import com.example.demo.extra.Chatjson;
import com.example.demo.extra.Getleveljson;
import com.example.demo.extra.Gpsjson;
import com.example.demo.extra.Leveljson;
import com.example.demo.extra.Loginjson;
import com.example.demo.extra.Mappingjson;
import com.example.demo.extra.PatiantDetailsjson;
import com.example.demo.extra.Profilepicjson;
import com.example.demo.extra.Signupjson;
import com.example.demo.extra.UserdetailsResponse;

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
	
	
	// (2) =========registration====================================
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody Signupjson signupjson){
		try {
			if(signupjson.getUsername().length()>0 && signupjson.getPassword().length()>0) {
				if(allusersService.update_tables(signupjson)) {
					AllUsers alluser=allusersService.getuserfrom_username_password(signupjson.getUsername(),signupjson.getPassword());
					if(alluser.getType().equals("user")) {
						//also update stress level and history
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
			}else {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("required user name and password !");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
		
	}
	
	// (1) =============login===================================
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Loginjson lj){
		
		try {
			if(allusersService.validate_login(lj.getUsername(),lj.getPassword())) {
				//System.out.println(lj.getUsername()+" "+lj.getPassword());
				AllUsers u3=allusersService.getuserfrom_username_password(lj.getUsername(),lj.getPassword());
				return ResponseEntity.ok(u3);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid username or password");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
	}
	
	
	// (3) ==============update stresslevel of user=======================
	@RequestMapping(value="/level",method=RequestMethod.POST)
	public ResponseEntity<?> level_update(@RequestBody Leveljson leveljson){
		try {
			if(userService.update_user_stress_level(leveljson.getId(),leveljson.getLevel())) {
				// update history table
				 boolean g=stressLevelHistoryService.update_history_strees_level_details(leveljson.getId(), leveljson.getLevel());
				return ResponseEntity.ok("success!!");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail!! cannot find user");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid user input");
		}
		
		
	}
	
	
	// (4) ==================update user current gps location=========================
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
	
	// (5) ===================update counceller gps location===================
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
	
	// (6) ================change profile picture=======================
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
	
	
	// (7) =====================get user stress level=============================
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
	
	
	
	// (8) =================map councellers by user======================
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
	
	
		
		
		
		// (9)==================online chat with  counceller vs users==============================
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
			
		
		

		
		// (10) ======================view user details by counceller============================
		@RequestMapping(value="/PatientDetails",method=RequestMethod.POST)
		public ResponseEntity<?> getpatiantDetails(@RequestBody PatiantDetailsjson details){
			try {
				if(allusersService.is_user_exist(details.getPatient_id()) && allusersService.is_user_exist(details.getCounceller_id())
						&& allusersService.getuserfrom_id(Integer.parseInt(details.getPatient_id())).getType().equals("user")
						&& allusersService.getuserfrom_id(Integer.parseInt(details.getPatient_id())).getStatus().equals("enable")
						&& allusersService.getuserfrom_id(Integer.parseInt(details.getCounceller_id())).getType().equals("counceller")
						&& allusersService.getuserfrom_id(Integer.parseInt(details.getCounceller_id())).getStatus().equals("enable")) 
				{
				
					if(mapService.is_counceller_already_exist( details.getPatient_id(),details.getCounceller_id())) {
						AllUsers uu2=allusersService.getuserfrom_id(Integer.parseInt(details.getPatient_id()));
						User uu3=userService.getUserby_id(details.getPatient_id());
						UserdetailsResponse userdata=new UserdetailsResponse();
						userdata.setAddress(uu2.getAddress());
						userdata.setAge(uu2.getAge().toString());
						userdata.setBirth_date(uu2.getBirth_date());
						userdata.setEmail(uu2.getEmail());
						userdata.setGender(uu2.getGender());
						userdata.setGps_location(uu3.getGps_location().toString());
						userdata.setGuadiant_phone_no(uu3.getGuadiant_phone_no());
						userdata.setId(uu2.getId().toString());
						userdata.setJob(uu3.getJob());
						userdata.setName(uu2.getName());
						userdata.setPhone_number(uu2.getPhone_number());
						userdata.setStress_level(uu3.getStress_level().toString());
						
						return ResponseEntity.ok(userdata);
						
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not your patient");
					}
					
					
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not valid user or Counceller");
				}
				
				
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user inputs!!");
			}
				
			
		}		
		
		
		
		// (11) =============================controll conceller access by admin==============================
		@RequestMapping(value="/admin/accessControll",params= {"admin_id","controller_id","status"}, method=RequestMethod.GET)
		public ResponseEntity<?> accessControll(@RequestParam("admin_id") String admin_id,@RequestParam("controller_id") String controller_id,@RequestParam("status") String status){
			
			try {
				//System.out.println(admin_id+controller_id+status);
				if(allusersService.is_user_exist(admin_id) && allusersService.is_user_exist(controller_id)) {
					AllUsers adminob=allusersService.getuserfrom_id(Integer.parseInt(admin_id));
					AllUsers userob=allusersService.getuserfrom_id(Integer.parseInt(controller_id));
					if(adminob.getType().equals("admin")) {
						if(userob.getType().equals("counceller") || userob.getType().equals("user")) {
							if(status.equals("enable") || status.equals("disable")) {
								userob.setStatus(status);
								allusersService.updateAlluserInstance(userob);
								return ResponseEntity.ok("access controlled success!! ");
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status ");
							}
							
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user or Counceller ");
						}
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Admin ");
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot find users");
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid user inputs");
			}
			
			
			//return ResponseEntity.ok("lol");
		}	
					
		
		
		// (12) ====================add music track by counceller=================================
		@RequestMapping(value="/addTrack",method=RequestMethod.POST)
		public ResponseEntity<?> addMusicTrack(@RequestBody AddTrackjson addTrackjson){
			try {
				if(allusersService.is_user_exist(addTrackjson.getCounceller_id())) {
					if((allusersService.getuserfrom_id(Integer.parseInt(addTrackjson.getCounceller_id())).getType().equals("counceller")
							&& allusersService.getuserfrom_id(Integer.parseInt(addTrackjson.getCounceller_id())).getStatus().equals("enable")) ||
							(allusersService.getuserfrom_id(Integer.parseInt(addTrackjson.getCounceller_id())).getType().equals("admin"))) 
					{
						if(Double.parseDouble(addTrackjson.getMax_stress_level())> Double.parseDouble(addTrackjson.getMin_stress_level())) {
							if(musicTrackService.is_duplicate_name_exist(addTrackjson.getName())) {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the track name is already exist!!");
							}else {
								musicTrackService.updateMusicTracktable(addTrackjson);
								return ResponseEntity.ok("update success");
							}
							
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("max stress level should grater than min stress level");
						}
						
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a valid Counceller");
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot find Counceller");
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user inputs");
			}
			
			
			
			
		}
			
		
	
		
		// (13) ===============controll music track by admin==========================
		@RequestMapping(value="/admin/controllTracks",params= {"admin_id","track_id","status"},method=RequestMethod.GET)
		public ResponseEntity<?> music_track_controll(@RequestParam("admin_id") String admin_id,@RequestParam("track_id") String track_id,@RequestParam("status") String status){
			
			try {
				if(allusersService.is_user_exist(admin_id) ) {
					if(allusersService.getuserfrom_id(Integer.parseInt(admin_id)).getType().equals("admin")) {
						if(musicTrackService.is_exist_by_id(Long.parseLong(track_id))) {
							if(status.equals("delete")) {
								musicTrackService.delete_track_by_id(Long.parseLong(track_id));
								return ResponseEntity.ok(" delete successfully!!");
							}else if(status.equals("enable") || status.equals("disable")) {
								MusicTrack track=new MusicTrack();
								track=musicTrackService.get_instance_by_id(Integer.parseInt(track_id));
								track.setStatus(status);
								musicTrackService.update_instance(track);
								return ResponseEntity.ok("status changed!!");
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status");
							}
							
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot find music Track");
						}
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid admin");
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot find Admin ");
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid user inputs ");
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
	
/*
// (14) ==================update account details=================================
	@RequestMapping(value="/accountSetting",method=RequestMethod.POST)
	public ResponseEntity<?> updateAccountDetails(@RequestBody AccountSettingjson account){
		
		if(allusersService.is_user_exist(account.getId())) {
				AllUsers user=new AllUsers();
				user=allusersService.getuserfrom_id(Integer.parseInt(account.getId()));
				if(account.getAddress().length()>0) {
					user.setAddress(account.getAddress());
				}
				if(account.getBirth_date().length()>0) {
					user.setBirth_date(account.getBirth_date());
				}if(account.getEmail().length()>0) {
					user.setEmail(account.getEmail());
				}if(account.getGender().length()>0) {
					user.setGender(account.getGender());
				}if(account.getName().length()>0) {
					user.setName(account.getName());
				}if(account.getUsername().length()>0) {
					user.setUsername(account.getUsername());
				}if(account.getPassword().length()>0) {
					user.setPassword(account.getPassword());
				}if(account.getPhone_number().length()>0) {
					user.setPhone_number(account.getPhone_number());
				}
			if(allusersService.getuserfrom_id(Integer.parseInt(account.getId())).getType().equals("user")) {
				
			}else if(allusersService.getuserfrom_id(Integer.parseInt(account.getId())).getType().equals("counceller")) {
				return ResponseEntity.ok("lol");
			}else if(allusersService.getuserfrom_id(Integer.parseInt(account.getId())).getType().equals("admin")) {
				return ResponseEntity.ok("lol");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a valid user");
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot find user");
		}
		
		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
