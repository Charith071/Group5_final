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

import com.example.demo.Entity.AdminNotification;
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
import com.example.demo.commonFunction.CommnFunction;
import com.example.demo.dao.MapDao;
import com.example.demo.extra.AccountSettingjson;
import com.example.demo.extra.AddTrackjson;
import com.example.demo.extra.AdminNotificationjson;
import com.example.demo.extra.Chatjson;
import com.example.demo.extra.Getleveljson;
import com.example.demo.extra.Gpsjson;
import com.example.demo.extra.JsonResponse;
import com.example.demo.extra.Leveljson;
import com.example.demo.extra.Loginjson;
import com.example.demo.extra.Mappingjson;
import com.example.demo.extra.PatiantDetailsjson;
import com.example.demo.extra.Profilepicjson;
import com.example.demo.extra.Return_User_Stress_leveljson;
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
	
	
	private CommnFunction func=new CommnFunction();
	
	private JsonResponse jsonResponse=new JsonResponse();
	
	
	
	
	
	

	@RequestMapping("")
	public ResponseEntity<?> home(){
	
		return ResponseEntity.ok(new JsonResponse("Home ","success"));
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
						return ResponseEntity.ok(new JsonResponse("user registration is succcess!! ","success"));
					}else if(alluser.getType().equals("counceller")) {
						councellerService.update_table(signupjson,alluser.getId());
						return ResponseEntity.ok(new JsonResponse("Counceller registration is succcess!! ","success"));
					}else {
						return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse("invalid user input ","fail"));
					}
					//return ResponseEntity.ok("Counceller registration is succcess!!");
				}else {
					return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("registration fail...change username or password ","fail"));
				}
			}else {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("required user name and password ! ","fail"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse("invalid user input ","fail"));
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
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("invalid username or password ","fail"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse("invalid user input ","fail"));
		}
		
	}
	
	
	// (3) ==============update stresslevel of user=======================
	@RequestMapping(value="/level",method=RequestMethod.POST)
	public ResponseEntity<?> level_update(@RequestBody Leveljson leveljson){
		try {
			if(userService.update_user_stress_level(leveljson.getId(),leveljson.getLevel())) {
				// update history table
				 boolean g=stressLevelHistoryService.update_history_strees_level_details(leveljson.getId(), leveljson.getLevel());
				return ResponseEntity.ok(new JsonResponse("update success ","success"));
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("update fail!! cannot find user ","fail"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
		}
		
		
	}
	
	
	// (4) ==================update user current gps location=========================
	@RequestMapping(value="/user/gps",method=RequestMethod.POST)
	public ResponseEntity<?> update_user_gps_location( @RequestBody Gpsjson gpsjson){
		try {
			if(userService.update_user_gps_location(gpsjson)) {
				return ResponseEntity.ok(new JsonResponse(" gps update success!! ","success"));
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(" cannot find user ","fail"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
		}
		
		
	}
	
	// (5) ===================update counceller gps location===================
	@RequestMapping(value="/counceller/gps",method=RequestMethod.POST)
	public ResponseEntity<?> update_counceller_gps( @RequestBody Gpsjson gpsjson1){
		try {
			if(councellerService.update_gps_location(gpsjson1)) {
				return ResponseEntity.ok(new JsonResponse(" update success!! ","success"));
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(" update fail!! cannot find Counceller ","fail"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
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
						return ResponseEntity.ok(new JsonResponse(" update success!! ","success"));
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("  update fail!! , Cannot find user ","fail"));
					}
				}else if(au1.getType().equals("counceller")) {
					if(councellerService.change_profile(profilepicjson)) {
						return ResponseEntity.ok(new JsonResponse(" update successs!! ","success"));
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(" update fail!! , Cannot find Counceller ","fail"));
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(" update fail!! , Cannot find ","fail"));
				}
				
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(" user is not exist! ","fail"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
		}
		
		
	}
	
	
	// (7) =====================get user stress level=============================
	@RequestMapping(value="/getLevel",method=RequestMethod.POST)
	public ResponseEntity<?> getStressLevel(@RequestBody Getleveljson getleveljson){
		
		try {
			if(userService.is_user_exist(getleveljson.getId())) {
				User u1=userService.getUserby_id(getleveljson.getId());
				
				//return ResponseEntity.ok(u1.getStress_level());
				return ResponseEntity.ok(new Return_User_Stress_leveljson(u1.getStress_level().toString(),"stress Level","success"));
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("cannot find user","fail"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
		}
		
	}
	
	
	
	// (8) =================map councellers by user======================
		@RequestMapping(value="/user/mapCounceller",method=RequestMethod.POST)
		public ResponseEntity<?> mapcounceller(@RequestBody Mappingjson mappingjson){
			try {
			
				if(allusersService.getuserfrom_id(Integer.parseInt(mappingjson.getUser_id())).getType().equals("user") && allusersService.getuserfrom_id(Integer.parseInt(mappingjson.getCounceller_id())).getType().equals("counceller")) {
					
					if(mapService.numberOfinstance_by_userId(mappingjson.getUser_id())==1 || mapService.numberOfinstance_by_userId(mappingjson.getUser_id())==0) {
						if(mapService.is_counceller_already_exist(mappingjson.getUser_id(), mappingjson.getCounceller_id())) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("alredy has a this counceller","fail"));
						}else {
							if(mapService.updateMapDetails(mappingjson.getUser_id(),mappingjson.getCounceller_id())) {
								return ResponseEntity.ok(new JsonResponse("counceller mapping  success","success"));
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("mapping fail","fail"));
							}
						}
						
						
					
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("u have already two councellers","fail"));
					}
					
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("user or counceller does not exist","fail"));
				}
				
				
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse("invalid user input","fail"));
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
									return ResponseEntity.ok(new JsonResponse("msg details update successs!!","success"));
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("cannot update table!","fail"));
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Please map the counceller and re try","fail"));
							}
						}else if(sender.getType().equals("counceller")) {
							//=====from counceler to user============
							if(mapService.is_counceller_already_exist(chatjson.getReceiver_id(), chatjson.getSender_id())) {
								if(messagesService.update_msg_details(chatjson)) {
									return ResponseEntity.ok(new JsonResponse("msg details update successs!!","success"));
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("cannot update table!","fail"));
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Please map the counceller and re try","fail"));
							}
							//return ResponseEntity.ok("jshajdh");
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Access Denied!!","fail"));
						}
						
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Not a valid user or Counceller","fail"));
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("user or counceller does not exist!!","fail"));
				}
			
			
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse("invalid user input","fail"));
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
						userdata.setRes_status("success");
						
						
						return ResponseEntity.ok(userdata);
						
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Not your patient","fail"));
					}
					
					
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Not valid user or Counceller","fail"));
				}
				
				
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("invalid user input","fail"));
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
								return ResponseEntity.ok(new JsonResponse("access controlled success!! ","success"));
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid status ","fail"));
							}
							
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user or Counceller ","fail"));
						}
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid Admin ","fail"));
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find users","fail"));
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("invalid user input","fail"));
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
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("the track name is already exist!!","fail"));
							}else {
								musicTrackService.updateMusicTracktable(addTrackjson);
								return ResponseEntity.ok(new JsonResponse("update success","success"));
							}
							
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("max stress level should grater than min stress level","fail"));
						}
						
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Not a valid Counceller","fail"));
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find Counceller","fail"));
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
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
								return ResponseEntity.ok(new JsonResponse(" delete successfully!!","success"));
							}else if(status.equals("enable") || status.equals("disable")) {
								MusicTrack track=new MusicTrack();
								track=musicTrackService.get_instance_by_id(Integer.parseInt(track_id));
								track.setStatus(status);
								musicTrackService.update_instance(track);
								return ResponseEntity.ok(new JsonResponse("status changed!!","success"));
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid status","fail"));
							}
							
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find music Track","fail"));
						}
					}else {
					
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid admin","fail"));
					}
				}else {
					
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find Admin","fail"));
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("invalid user inputs ","fail"));
			}
			
			
			
			
		}
		
		
		
		
		// (14) ==================update account details=================================
		@RequestMapping(value="/accountSetting",method=RequestMethod.POST)
		public ResponseEntity<?> updateAccountDetails(@RequestBody AccountSettingjson account){
			
			try {
				if(allusersService.is_user_exist(account.getId())) {
					if(allusersService.getuserfrom_id(Integer.parseInt(account.getId())).getStatus().equals("enable")) {
						AllUsers user=allusersService.getuserfrom_id(Integer.parseInt(account.getId()));
						boolean is_password_duplicate=false;
						boolean is_username_duplicate=false;
						
						if(account.getUsername().length()>0 || account.getPassword().length()>0) {
							
							if(account.getPassword().length()>0 && account.getUsername().length()>0 ){
								if(allusersService.check_is_duplicate_user(account.getUsername(), account.getPassword())) {
									is_password_duplicate=true;
									is_username_duplicate=true;
									System.out.println(account.getUsername()+ account.getPassword());
								}else {
									user.setUsername(account.getUsername());
									user.setPassword(func.string_encript(account.getPassword()));
								}
							}else if(account.getUsername().length()>0) {
								if(allusersService.check_is_duplicate_user(account.getUsername(), func.string_decode(user.getPassword()))) {
									is_username_duplicate=true;
								}else {
									user.setUsername(account.getUsername());
								}
							}else if(account.getPassword().length()>0) {
								if(allusersService.check_is_duplicate_user(user.getUsername(), account.getPassword())) {
									is_password_duplicate=true;
								}else {
									user.setPassword(func.string_encript(account.getPassword()));
								}
							}
							
						}
						
						
						if(is_password_duplicate || is_username_duplicate) {
							if(is_password_duplicate) {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("please change password!!","fail"));
							}else if(is_username_duplicate) {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("please change username!!","fail"));
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("please change username or password","fail"));
							}
						}else {
							if(account.getAddress().length()>0) {
								user.setAddress(account.getAddress());
							}if(account.getBirth_date().length()>0) {
								user.setBirth_date(account.getBirth_date());
								user.setAge(func.cal_age(account.getBirth_date()));
							}if(account.getEmail().length()>0) {
								user.setEmail(account.getEmail());
							}if(account.getGender().length()>0) {
								user.setGender(account.getGender());
							}if(account.getName().length()>0) {
								user.setName(account.getName());
							}if(account.getPhone_number().length()>0) {
								user.setPhone_number(account.getPhone_number());
							}
							
							if(allusersService.update_Edited_account_detals(user)) {
								if(user.getType().equals("user")) {
									User uu=userService.getUserby_id(account.getId());
									if(account.getGuadiant_phone_no().length()>0) {
										uu.setGuadiant_phone_no(account.getGuadiant_phone_no());
									}if(account.getProfile_pic_name().length()>0) {
										uu.setProfile_pic_name(account.getProfile_pic_name()+account.getId());
									}if(account.getJob().length()>0) {
										uu.setJob(account.getJob());
									}
									if(userService.update_edited_user_details(uu)) {
										return ResponseEntity.ok(new JsonResponse("Update success 1","success"));
									}else {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("update fail 2 ","fail"));
									}
									
								}else if(user.getType().equals("counceller")) {
									Counceller cc=councellerService.get_counceller_by_id(account.getId());
									if(account.getCertificate().length()>0) {
										cc.setCertificate(account.getCertificate());
									}if(account.getQualification().length()>0) {
										cc.setQualification(account.getQualification());
									}
									
									if(councellerService.update_Edited_counceller_details(cc)) {
										return ResponseEntity.ok(new JsonResponse("Update success 1","success"));
									}else {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("update fail 3 ","fail"));
									}
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("update fail 4 ","fail"));
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("update fail 1 ","fail"));
							}
							
							
						}
						
						
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Not a valid user","fail"));
					}
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find user","fail"));
			}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
			}
			
			
			
			
		}
		
		
		
		

		// (15)=====================add admin_notification by admin==========================
			@RequestMapping(value="/admin/addNotification",method=RequestMethod.POST)
			public ResponseEntity<?> add_admin_notification(@RequestBody AdminNotificationjson notice){
				
				try {
					if(allusersService.getuserfrom_id(Integer.parseInt(notice.getAdmin_id())).getType().equals("admin")) {
						if(notice.getType().equals("all") || notice.getType().equals("user") || notice.getType().equals("counceller")) {
							AdminNotification nn=new AdminNotification();
							nn.setDate_time(func.getCurrentdateTime());
							nn.setMsg(notice.getNotice());
							nn.setStatus("enable");
							nn.setType(notice.getType());
							if(adminNotificationService.update_admin_notifcation(nn)) {
								return ResponseEntity.ok(new JsonResponse("Notice Entered Success!!","success"));
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot enter Notice","fail"));
							}
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid  type","fail"));
						}
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid  admin id","fail"));
						}
						
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
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
	

	
	/*//  (16)============controlling notice by admin==========================
	@RequestMapping(value="/admin/ControllNotice",method=RequestMethod.POST)
	public ResponseEntity<?> Controll_notice(@RequestBody AdminNotificationjson noty){
		
		try {
			if(allusersService.getuserfrom_id(Integer.parseInt(noty.getAdmin_id())).getType().equals("admin")) {
				
				if(adminNotificationService.is_notice_exisit(noty.getNotice_id())) {
					AdminNotification note=adminNotificationService.get_instace_by_id(noty.getNotice_id());
					if(noty.getType().equals("delete")) {
						if(adminNotificationService.delete_by_id(noty.getNotice_id())) {
							return ResponseEntity.ok("delete Success!!");
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete");
						}
					}else {
							if(noty.getType().equals("enable") || noty.getType().equals("disable")) {
								
								note.setStatus(noty.getStatus());
								if(adminNotificationService.update_admin_notifcation(note)) {
									return ResponseEntity.ok("update success");
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail");
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Invalid status");
							}
					}
					
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot find notice");
				}
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid admin id");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid User  Inputs");
		}
		
		
	}*/
 	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
	
	
	
	

