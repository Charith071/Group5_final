package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.awt.PageAttributes.MediaType;
import java.io.Console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.AdminNotification;
import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.Booking_details;
import com.example.demo.Entity.Booking_request;
import com.example.demo.Entity.Changes;
import com.example.demo.Entity.Counceller;
import com.example.demo.Entity.Map;
import com.example.demo.Entity.Messages;
import com.example.demo.Entity.MusicTrack;
import com.example.demo.Entity.TableInfomation;
import com.example.demo.Entity.Tips;
import com.example.demo.Entity.User;
import com.example.demo.Service.AdminNotificationService;
import com.example.demo.Service.AllusersService;
import com.example.demo.Service.BookingDetailsService;
import com.example.demo.Service.BookingRequestService;
import com.example.demo.Service.ChangesService;
import com.example.demo.Service.CouncellerService;
import com.example.demo.Service.MapService;
import com.example.demo.Service.MessagesService;
import com.example.demo.Service.MusicTrackService;
import com.example.demo.Service.StressLevelHistoryService;
import com.example.demo.Service.TableInformationService;
import com.example.demo.Service.TipsService;
import com.example.demo.Service.UserService;
import com.example.demo.commonFunction.CommnFunction;
import com.example.demo.dao.CouncellerDao;
import com.example.demo.dao.MapDao;
import com.example.demo.extra.AccessControlJson;
import com.example.demo.extra.AccountSettingjson;
import com.example.demo.extra.AddTipsjson;
import com.example.demo.extra.AddTrackjson;
import com.example.demo.extra.AdminNotificationjson;
import com.example.demo.extra.BookingRequestJson;
import com.example.demo.extra.Chatjson;
import com.example.demo.extra.Confirmjson;
import com.example.demo.extra.DeleteRequestJson;
import com.example.demo.extra.Getdatjson;
import com.example.demo.extra.Getleveljson;
import com.example.demo.extra.Gpsjson;
import com.example.demo.extra.JsonResponse;
import com.example.demo.extra.Leveljson;
import com.example.demo.extra.LoginOutPut;
import com.example.demo.extra.Loginjson;
import com.example.demo.extra.Loginout;
import com.example.demo.extra.Mappingjson;
import com.example.demo.extra.PatiantDetailsjson;
import com.example.demo.extra.Profilepicjson;
import com.example.demo.extra.RealtimeOut;
import com.example.demo.extra.Realtimejson;
import com.example.demo.extra.ResponseJson;
import com.example.demo.extra.Return_User_Stress_leveljson;
import com.example.demo.extra.Signupjson;
import com.example.demo.extra.UserdetailsResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
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
	@Autowired
	private TableInformationService tableInformationService;
	@Autowired
	private ChangesService changesService;
	
	
	private CommnFunction func=new CommnFunction();
	
	private JsonResponse jsonResponse=new JsonResponse();
	
	
	
	
	
	

	@RequestMapping("")
	public ResponseEntity<?> home(HttpSession s){
		try {
			if(s.getAttribute("type").equals("user") || s.getAttribute("type").equals("admin") || s.getAttribute("type").equals("counceller")) {
				return ResponseEntity.ok(new JsonResponse(s.getAttribute("id").toString(),"success"));
			}else {
				return ResponseEntity.ok(new JsonResponse("dsda".toString(),"fail"));
			}
			
		} catch (Exception e) {
			return ResponseEntity.ok(new JsonResponse("Invalid user input ","fail"));
		}
		
		
	}
	
	
	// (2) =========registration===========================================================
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody Signupjson signupjson){
		try {
			if(signupjson.getUsername().length()>0 && signupjson.getPassword().length()>0 ) {
				try {
					Double gps1=Double.parseDouble(signupjson.getLatitude());
					Double gps2=Double.parseDouble(signupjson.getLongitude());
					
					if(allusersService.update_tables(signupjson)) {
						AllUsers alluser=allusersService.getuserfrom_username_password(signupjson.getUsername(),signupjson.getPassword());
						if(alluser.getType().equals("user")) {
							//also update stress level and history
							
								userService.update_table(signupjson,alluser.getId());
								return ResponseEntity.ok(new JsonResponse(alluser.getId().toString(),"success"));
							
							
						}else if(alluser.getType().equals("counceller")) {
							councellerService.update_table(signupjson,alluser.getId());
							changesService.update_changed_table(new Changes("all_users",alluser.getId(),"Join New Counceller",Long.parseLong("1"),"false","notification",func.getCurrentdateTime()));
							//changesService.update_changed_table(new Changes("all_users",alluser.getId(),"Access Denied By Administrator",Long.parseLong(alluser.getId().toString()),"false","notification"));
							return ResponseEntity.ok(new JsonResponse(alluser.getId().toString(),"success"));
						}else {
							return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse("invalid user input ","fail"));
						}
						//return ResponseEntity.ok("Counceller registration is succcess!!");
					}else {
						return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("registration fail...change username or password ","fail"));
					}
				} catch (Exception e) {
					return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("registration fail..","fail"));
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
				if(u3.getStatus().equals("enable")) {
					u3.setLastLogin(func.getCurrentdateTime());
					allusersService.updateAlluserInstance(u3);
					Loginout out=new Loginout("login success", "success", u3.getId().toString(), u3.getName(), u3.getAge().toString(), u3.getAddress(), u3.getBirth_date(), u3.getCreate_date(), u3.getEmail(), u3.getGender(), u3.getPhone_number(), u3.getType(), "add in Future ");
					return ResponseEntity.ok(out);
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Access Denied By Administrator","fail"));
				}
				
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("invalid username or password ","fail"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse("invalid user input ","fail"));
		}
		
	}
	
	/*//========logout=================================
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public ResponseEntity<?> logout(HttpSession session){
		session.removeAttribute("id");
		session.removeAttribute("type");
		session.invalidate();
		return ResponseEntity.ok(new JsonResponse("loged out","success"));
	}
	*/
	
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
								Map m=mapService.getInstanceBy_councellerId_and_userId(mappingjson.getCounceller_id(), mappingjson.getUser_id());
								changesService.update_changed_table(new Changes("map", m.getId(), "Counceller Was Mapped", Long.parseLong(mappingjson.getCounceller_id()), "false", "notification",func.getCurrentdateTime()));
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
									Messages m=messagesService.get_last_instance_by_Receiver_id(chatjson.getReceiver_id());
									changesService.update_changed_table(new Changes("messages", m.getId(), "New Message", m.getReceiver(), "false", "messages",func.getCurrentdateTime()));
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
						userdata.setLatitude(uu3.getLatitude().toString());
						userdata.setLongitude(uu3.getLongitude().toString());
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
		@RequestMapping(value="/admin/accessControll", method=RequestMethod.POST)
		public ResponseEntity<?> accessControll(@RequestBody AccessControlJson para){
			
			try {
				//System.out.println(admin_id+controller_id+status);
				if(allusersService.is_user_exist(para.getAdmin_id()) && allusersService.is_user_exist(para.getController_id())) {
					AllUsers adminob=allusersService.getuserfrom_id(Integer.parseInt(para.getAdmin_id()));
					AllUsers userob=allusersService.getuserfrom_id(Integer.parseInt(para.getController_id()));
					if(adminob.getType().equals("admin")) {
						if(userob.getType().equals("counceller") || userob.getType().equals("user")) {
							if(para.getStatus().equals("enable") || para.getStatus().equals("disable")) {
								userob.setStatus(para.getStatus());
								allusersService.updateAlluserInstance(userob);
								
								//========update changes table==============
								
									/*if(status.equals("disable")) {
										changesService.update_changed_table(new Changes("all_users",userob.getId(),"Access Denied By Administrator",Long.parseLong(userob.getId().toString()),"false","notification"));
									}else {
										changesService.update_changed_table(new Changes("all_users",userob.getId(),"Access Confirm By Administrator",Long.parseLong(userob.getId().toString()),"false","notification"));
									}*/
								
								//============================================
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
								MusicTrack m=musicTrackService.getLastRecorde();
								changesService.update_changed_table(new Changes("music_track", m.getId(), "Add New Music Track ", Long.parseLong("1"), "false", "notification", func.getCurrentdateTime()));
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
								AdminNotification n=adminNotificationService.getLastRecorde();
								if(notice.getType().equals("user")) {
									changesService.update_changed_table(new Changes("admin_notification", n.getId(), "New Admin Notice", Long.parseLong("100000"), "false", "other", func.getCurrentdateTime()));
								}else if(notice.getType().equals("counceller")) {
									changesService.update_changed_table(new Changes("admin_notification", n.getId(), "New Admin Notice", Long.parseLong("100001"), "false", "other", func.getCurrentdateTime()));
								}else {
									changesService.update_changed_table(new Changes("admin_notification", n.getId(), "New Admin Notice", Long.parseLong("100002"), "false", "other", func.getCurrentdateTime()));

								}
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
		
		
		//  (16)============controlling notice by admin==========================
			@RequestMapping(value="/admin/ControllNotice",method=RequestMethod.POST)
			public ResponseEntity<?> Controll_notice(@RequestBody AdminNotificationjson noty){
				
				try {
					if(allusersService.is_user_exist(noty.getAdmin_id())) {
						if(allusersService.getuserfrom_id(Integer.parseInt(noty.getAdmin_id())).getType().equals("admin")) {
							if(adminNotificationService.is_notice_exisit(noty.getNotice_id())) {
								AdminNotification note=adminNotificationService.get_instace_by_id(noty.getNotice_id());
								if(noty.getStatus().equals("delete")) {
									adminNotificationService.delete_by_id(noty.getNotice_id());
									return ResponseEntity.ok(new JsonResponse("Notice deleted","success"));
								}else if(noty.getStatus().equals("enable")) {
									note.setStatus("enable");
									adminNotificationService.update_admin_notifcation(note);
									return ResponseEntity.ok(new JsonResponse("status update success","success"));
								}else if(noty.getStatus().equals("disable")) {
									note.setStatus("disable");
									adminNotificationService.update_admin_notifcation(note);
									return ResponseEntity.ok(new JsonResponse("status update success","success"));
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid Status","fail"));
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find Notification","fail"));
							}
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid Admin","fail"));
						}
						
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find Admin","fail"));
					}
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
					
				}
				
				
				
			}
		
			// (17) ================send bookinng request by patient to counceller===============================
				@RequestMapping(value="/user/Request",method=RequestMethod.POST)
				public ResponseEntity<?> send_request_to_counceller(@RequestBody BookingRequestJson req){
							try {
								if(allusersService.is_user_exist(req.getUser_id())) {
									if(allusersService.is_user_exist(req.getCounceller_id())) {
										AllUsers u=allusersService.getuserfrom_id(Integer.parseInt(req.getUser_id()));
										AllUsers c=allusersService.getuserfrom_id(Integer.parseInt(req.getCounceller_id()));
										if(u.getType().equals("user") && u.getStatus().equals("enable") && c.getType().equals("counceller") && c.getStatus().equals("enable")) {
											if(mapService.is_counceller_already_exist(req.getUser_id(), req.getCounceller_id())) {
												Booking_request bb=new Booking_request();
												bb.setCounceller_id(Long.parseLong(req.getCounceller_id()));
												String dd=func.getCurrentdateTime();
												bb.setDate_time(dd);
												bb.setUser_id(Long.parseLong(req.getUser_id()));
												bb.setStatus("true");
												if(bookingRequestService.update_booking_request(bb)) {
													Booking_request req2=bookingRequestService.get_instance_by_councellerId_userId_status_date(req.getCounceller_id(), req.getUser_id(), dd);
													changesService.update_changed_table(new Changes("booking_request",req2.getId(), "You Have New Booking Request", Long.parseLong(req.getCounceller_id()), "false", "notification",func.getCurrentdateTime()));
													
													return ResponseEntity.ok(new JsonResponse("request updated!","success"));
												}else {
													return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot update table","fail"));

												}
											}else {
												return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Initialy you want to map this counceller","fail"));
											}
										}else {
											return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user or counceller","fail"));
										}
									}else {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Connot find Counceller","fail"));
									}
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Connot find user","fail"));
								}
							} catch (Exception e) {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));

							}		
							
				}
				
				
				
				
				
		//(18) ==========delete submited booking  request by user==========================================
		@RequestMapping(value="/user/deleterequest",method=RequestMethod.POST)				
		public ResponseEntity<?> delete_request(@RequestBody DeleteRequestJson param){
			try {
				if(allusersService.is_user_exist(param.getUser_id())) {
					if(allusersService.getuserfrom_id(Integer.parseInt(param.getUser_id())).getType().equals("user") && allusersService.getuserfrom_id(Integer.parseInt(param.getUser_id())).getStatus().equals("enable")) {
						if(bookingRequestService.is_request_exist_by_id(param.getRequest_id())) {
							Booking_request req=bookingRequestService.get_instance_by_id(param.getRequest_id());
							if(req.getUser_id().toString().equals(param.getUser_id())) {
								bookingRequestService.delete_request_by_id(param.getRequest_id());
								return ResponseEntity.ok(new JsonResponse("Request delete Successfully!","success"));
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find request","fail"));
							}
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find request","fail"));
						}
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user","fail"));
					}
							
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find user","fail"));
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
			}
					
		}
		
		
				
		//(19) ====================response to booking request by counceller===========================
		
			@RequestMapping(value="/counceller/Response",method=RequestMethod.POST)
			public ResponseEntity<?> Response_to_booking_request(@RequestBody ResponseJson para){
				try {
					if(allusersService.is_user_exist(para.getCounceller_id())) {
						AllUsers cc=allusersService.getuserfrom_id(Integer.parseInt(para.getCounceller_id()));
						if(cc.getType().equals("counceller") && cc.getStatus().equals("enable")) {
							if(bookingRequestService.is_request_exist_by_id(para.getRequest_id().toString())) {
								Booking_request bb=bookingRequestService.get_instance_by_id(para.getRequest_id());
								if(bb.getCounceller_id().toString().equals(para.getCounceller_id())) {
									//==========when counceller cansal the request counceller_status="disable"  =======================
									if(para.getCounceller_status().equals("disable")) {
										Booking_request re=bookingRequestService.get_instance_by_id(para.getRequest_id());
										re.setStatus("false");
										bookingRequestService.update_booking_request(re);
										changesService.update_changed_table(new Changes("booking_request", re.getId(), "Cansal Booking Request By Counceller", re.getUser_id(), "false", "notification",func.getCurrentdateTime()));
										
										return ResponseEntity.ok(new JsonResponse("Request Cansal","success"));
									}else if(para.getCounceller_status().equals("enable")){
										//=============when counceller accept the request and send details to user==============
										Booking_details data=new Booking_details();
										data.setDate_time(para.getDate_time());
										data.setDetails(para.getDetails());
										data.setLocation(para.getLocation());
										data.setPayment(Double.parseDouble(para.getPayment()));
										data.setStatus("disable");
										data.setRequestId(Long.parseLong(para.getRequest_id()));
										bookingDetailsService.update_booking_details(data);
										bb.setStatus("false");
										bookingRequestService.update_booking_request(bb);
										
										Booking_details bd=bookingDetailsService.get_instance_by_booking_request_id(para.getRequest_id());
										changesService.update_changed_table(new Changes("booking_details",bd.getId(), "Counceller Sent Your Booking Details Please Check And Confirm",bb.getUser_id(), "false", "notification",func.getCurrentdateTime()));
										return ResponseEntity.ok(new JsonResponse("Detail updated successfully!","success"));
									}else {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid counceller status","fail"));

									}
									
									
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find any request","fail"));
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find any request","fail"));
							}
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("invalid Counceller","fail"));
						}
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find Counceller","fail"));
					}
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid inputs","fail"));
				}
				
				
			}
			
					
				
				
	
		// (20) ================confirm counceller booking reply by patient==============================
			@RequestMapping(value="/user/confirm",method=RequestMethod.POST)
			public ResponseEntity<?> Confirm_counceller_booking_reply_by_user(@RequestBody Confirmjson para){
				try {
					if(allusersService.is_user_exist(para.getUser_id())) {
						AllUsers aa=allusersService.getuserfrom_id(Integer.parseInt(para.getUser_id()));
						if(aa.getType().equals("user") && aa.getStatus().equals("enable")) {
							if(bookingRequestService.is_request_exist_by_id(para.getBooking_request_id())) {
								Booking_request re=bookingRequestService.get_instance_by_id(para.getBooking_request_id());
								if(re.getUser_id().toString().equals(para.getUser_id())) {
									if(bookingDetailsService.is_details_exist_by_booking_request_id(para.getBooking_request_id())) {
										Booking_details dd=bookingDetailsService.get_instance_by_booking_request_id(para.getBooking_request_id());
										dd.setStatus("enable");
										bookingDetailsService.update_booking_details(dd);
										
										changesService.update_changed_table(new Changes("booking_details", dd.getId(), "Patient Confirm Your Booking Details",bookingRequestService.get_instance_by_id(dd.getRequestId().toString()).getCounceller_id(), "false", "notification",func.getCurrentdateTime()));
										return ResponseEntity.ok(new JsonResponse("Reply confirmed!!","success"));
									}else {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Reply is not exist!","fail"));
									}
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Reply is not for you","fail"));
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find any request","fail"));
							}
						
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user","fail"));
						}
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find user","fail"));
					}
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
				}
					
				
			}
						
						
		// (21)=============================add tips by counceller============================================
			@RequestMapping(value="/counceller/AddTips",method=RequestMethod.POST)
			public ResponseEntity<?> add_tips(@RequestBody AddTipsjson para){
				try {
					if(allusersService.is_user_exist(para.getCounceller_id())) {
						AllUsers user=allusersService.getuserfrom_id(Integer.parseInt(para.getCounceller_id()));
						if(user.getType().equals("counceller") && user.getStatus().equals("enable")) {
							Tips tip=new Tips();
							tip.setCouncellerId(Long.parseLong(para.getCounceller_id()));
							tip.setDateTime(func.getCurrentdate());
							tip.setStatus("enable");
							tip.setTip(para.getTip());
							tipsService.update_table(tip);
							Tips t=tipsService.getLastRecorde();
							changesService.update_changed_table(new Changes("tips", t.getId(), "Add New Tip", Long.parseLong("100000"), "false", "other", func.getCurrentdateTime()));
							return ResponseEntity.ok(new JsonResponse("Tips entered Success!!","success"));
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid Counceller","fail"));
						}
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find Counceller","fail"));
					}
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid inputs","fail"));
				}
				
				
			}
						
						
		
			
			
	
		//(23) get user,counceller,admin data by id.....
			@RequestMapping(value="/getData",method=RequestMethod.POST)
			public ResponseEntity<?> get_data(@RequestBody Getdatjson para){
				
				try {
					if(allusersService.is_user_exist(para.getId())) {
						AllUsers u=allusersService.getuserfrom_id(Integer.parseInt(para.getId()));
						if(u.getStatus().equals("enable")) {
							LoginOutPut data=new LoginOutPut(u.getId(), u.getName(), u.getAge(), u.getAddress(), u.getBirth_date(), u.getCreate_date(), u.getEmail(), u.getGender(), "Access Denied!", "Access DEnied!", u.getPhone_number(), u.getType(), u.getStatus(), Double.parseDouble("0"), Double.parseDouble("0"), "null", "null", "null", "null", "null",Float.parseFloat("0"),"success","success")	;									
							if(u.getType().equals("user")) {
								User u2=userService.getUserby_id(para.getId());
								data.setLatitude(u2.getLatitude());
								data.setLongitude(u2.getLongitude());
								data.setJob(u2.getJob());
								data.setGender(u2.getGuadiant_phone_no());
								data.setProfile_pic_name(u2.getProfile_pic_name());
								data.setStress_level(u2.getStress_level());
			
								return ResponseEntity.ok(data);
							}else if(u.getType().equals("counceller")) {
								Counceller c=councellerService.get_counceller_by_id(para.getId());
								data.setLatitude(c.getLatitude());
								data.setLongitude(c.getLongitude());
								data.setCertificate(c.getCertificate());
								data.setQualification(c.getQualification());
								data.setProfile_pic_name(c.getProfile_pic_name());
								return ResponseEntity.ok(data);
							}else if(u.getType().equals("admin")) {
								return	ResponseEntity.ok(data);
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid inputs", "fail"));
							}
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Access Denied", "fail"));
						}
						
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find user", "fail"));
					}
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs", "fail"));
				}
				
				
			}
		
						
						
					
						
						
						
	
						
		
		
		
						
//*****************************************************************************************	***********
						
  
	
			
	//(22) realtime=============================	
		@RequestMapping(value="/realtime",method=RequestMethod.POST)
		public ResponseEntity<?> test(@RequestBody Realtimejson para) throws ParseException{
			
			 String AdminNotification_status=adminNotificationService.getLastUptadeTime();
			 String AllUser_status=allusersService.getLastUptadeTime();
			 String BookingDetails_status=bookingDetailsService.getLastUptadeTime();
			 String BookingRequest_status=bookingRequestService.getLastUptadeTime();
			 String Counceller_status=councellerService.getLastUptadeTime();
			 String Map_status=mapService.getLastUptadeTime();
			 String Message_status=messagesService.getLastUptadeTime();
			 String MusicTrack_status=musicTrackService.getLastUptadeTime();
			 String StressLevelHistory_status=stressLevelHistoryService.getLastUptadeTime();
			 String Tips_status=tipsService.getLastUptadeTime();
			 String User_status=userService.getLastUptadeTime();
			 
		//	 changesService.update_changed_table(new Changes("all_users",alluser.getId(),"Join New Counceller",Long.parseLong("1"),"false","notification"));
			 
			
			boolean is_any_new_changes_found=false;
			RealtimeOut out=new RealtimeOut();
			AllUsers u=allusersService.getuserfrom_id(Integer.parseInt(para.getId()));
			List<Changes> l=null;
			if(changesService.is_exist_by_affected_user_id(para.getId())) {
				 l=(List<Changes>) changesService.get_all_insatnce_by_affected_id_and_notsend(para.getId());
				
				
				for(Changes c:l) {
					c.setIs_notification_send("true");
					c.setNotificationSendDate(func.getCurrentdate());
					changesService.update_changed_table(c);
					is_any_new_changes_found=true;
				}
				
			}
			
			Changes c1=changesService.get_last_instance();
			if(c1.getAffectedUserId().toString().equals("100000") || c1.getAffectedUserId().toString().equals("100001") || c1.getAffectedUserId().toString().equals("100002")) {
				String[] datetime=c1.getAddDate().split("T");
				String d=datetime[0];
				String t=datetime[1];
				String[] currentDate=func.getCurrentdateTime().split("T");
				if(d.equals(currentDate[0])) {
					
					
					SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
					Date date1 = format.parse(t);
					Date date2 = format.parse(currentDate[1]);
					long difference = (date2.getTime() - date1.getTime())/1000; 
					System.out.println(difference);
					if(difference <= 8) {
						if(u.getType().equals("user")) {				
							if(c1.getAffectedUserId().toString().equals("100000") || c1.getAffectedUserId().toString().equals("100002")) {
								l.add(c1);
								System.out.println(difference);
								is_any_new_changes_found=true;
							}							
						}else if(u.getType().equals("counceller")) {
							if(c1.getAffectedUserId().toString().equals("100001") || c1.getAffectedUserId().toString().equals("100002")) {
								l.add(c1);
								System.out.println(difference);
								is_any_new_changes_found=true;
							}	
						}
					}
					
				}						
				
			}
			
			
			
			
			
			if(is_any_new_changes_found) {
				
				out.setStatus(true);
				out.setList(l);
				return ResponseEntity.ok(out);
			}else {
				out.setStatus(false);
				return ResponseEntity.ok(out);
			}
			 
			
		}
		
				
				
		
				
						
						
	
			
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
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
		
		 
		 return ResponseEntity.ok(bookingRequestService.get_instance_by_councellerId_userId_status("5", "2","2018-05-19T17:32:24.490"));
	}
	*/
	

	
	
 	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
	
	
	
	

