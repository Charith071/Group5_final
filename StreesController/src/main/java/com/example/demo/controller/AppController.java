package com.example.demo.controller;



//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;


import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.ws.rs.core.HttpHeaders;

//import org.mockito.internal.verification.Only;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.AdminNotification;
import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.Booking_details;
import com.example.demo.Entity.Booking_request;
import com.example.demo.Entity.Changes;
import com.example.demo.Entity.Counceller;
import com.example.demo.Entity.Map;
import com.example.demo.Entity.Messages;
import com.example.demo.Entity.MusicTrack;
import com.example.demo.Entity.StressLevelHistory;
//import com.example.demo.Entity.TableInfomation;
import com.example.demo.Entity.Tips;
import com.example.demo.Entity.User;
import com.example.demo.Service.AdminNotificationService;
import com.example.demo.Service.AllusersService;
import com.example.demo.Service.BookingDetailsService;
import com.example.demo.Service.BookingRequestService;
import com.example.demo.Service.ChangesService;
import com.example.demo.Service.CouncellerService;
import com.example.demo.Service.KeyCloakService;
import com.example.demo.Service.KeycloakTokenValidater;
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
import com.example.demo.extra.ChangeCouncellerjson;
import com.example.demo.extra.Chatjson;
import com.example.demo.extra.Confirmjson;
import com.example.demo.extra.DeleteRequestJson;
import com.example.demo.extra.GetAllCouncellerOut;
import com.example.demo.extra.GetMappedCounceller;
import com.example.demo.extra.GetMappedCouncellers;
import com.example.demo.extra.GetMessageOut;
import com.example.demo.extra.GetMessagesJson;
import com.example.demo.extra.GetOneChatJson;
import com.example.demo.extra.GetTips;
import com.example.demo.extra.Getdatjson;
import com.example.demo.extra.Getleveljson;
import com.example.demo.extra.Gpsjson;
import com.example.demo.extra.GrtTipsOut;
//import com.example.demo.extra.HeaderData;
import com.example.demo.extra.IdOnly;
import com.example.demo.extra.JsonResponse;
import com.example.demo.extra.LevelHistoryjson;
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

import org.springframework.http.MediaType;

//import net.bytebuddy.jar.asm.commons.TryCatchBlockSorter;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*",allowedHeaders="*")
public class AppController {
	
	//http://localhost:4200
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
	@Autowired 
	private KeycloakTokenValidater keycloakTokenValidater;
	@Autowired
	private KeyCloakService keyCloakService;
	
	private CommnFunction func=new CommnFunction();
	
	private JsonResponse jsonResponse=new JsonResponse();
	
	
	@Value("${imageStore.path}")
	private String imagepath;
	
	
	

	@RequestMapping("")
	public ResponseEntity<?> home(){
		
		/*if(keycloakTokenValidater.Validate(h.getToken()).equals("admin")) {
			return ResponseEntity.ok(new JsonResponse("Authorized","success"));
		}else {
			return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
		}
				*/
			return ResponseEntity.ok("fdcfddf");
		
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
	public ResponseEntity<?> level_update(@RequestBody Leveljson leveljson ,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
		try {
			if(keycloakTokenValidater.Validate(token,secId,leveljson.getId()).equals("user")) {
				if(userService.update_user_stress_level(leveljson.getId(),leveljson.getLevel())) {
					// update history table
					 boolean g=stressLevelHistoryService.update_history_strees_level_details(leveljson.getId(), leveljson.getLevel());
					return ResponseEntity.ok(new JsonResponse("update success ","success"));
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("update fail!! cannot find user ","fail"));
				}
			}else {
				return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
		}
		
		
	}
	
	
	// (4) ==================update user current gps location=========================
	@RequestMapping(value="/user/gps",method=RequestMethod.POST)
	public ResponseEntity<?> update_user_gps_location( @RequestBody Gpsjson gpsjson,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
		try {
			if(keycloakTokenValidater.Validate(token,secId,gpsjson.getId()).equals("user")) {
				if(userService.update_user_gps_location(gpsjson)) {
					return ResponseEntity.ok(new JsonResponse(" gps update success!! ","success"));
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(" cannot find user ","fail"));
				}			}else {
				return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
		}
		
		
	}
	
	// (5) ===================update counceller gps location===================
	@RequestMapping(value="/counceller/gps",method=RequestMethod.POST)
	public ResponseEntity<?> update_counceller_gps( @RequestBody Gpsjson gpsjson1,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
		try {
			if(keycloakTokenValidater.Validate(token,secId,gpsjson1.getId()).equals("counceller")) {
				if(councellerService.update_gps_location(gpsjson1)) {
					return ResponseEntity.ok(new JsonResponse(" update success!! ","success"));
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(" update fail!! cannot find Counceller ","fail"));
				}			}else {
				return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
		}
		
		
	}
	
	// (6) ================change profile picture=======================
	@RequestMapping(value="/ChangeProfilePicture",method=RequestMethod.POST)
	public ResponseEntity<?> changeProfilePicture(@RequestBody Profilepicjson profilepicjson,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
		
		try {
			if(keycloakTokenValidater.Validate(token,secId,profilepicjson.getId()).equals("admin") || keycloakTokenValidater.Validate(token,secId,profilepicjson.getId()).equals("user") || keycloakTokenValidater.Validate(token,secId,profilepicjson.getId()).equals("counceller")) {
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
				}			}else {
				return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
		}
		
		
	}
	
	
	// (7) =====================get user stress level=============================
	@RequestMapping(value="/getLevel",method=RequestMethod.POST)
	public ResponseEntity<?> getStressLevel(@RequestBody Getleveljson getleveljson,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
		
		try {
			if(keycloakTokenValidater.Validate(token,secId,getleveljson.getId()).equals("user")) {
				if(userService.is_user_exist(getleveljson.getId())) {
					User u1=userService.getUserby_id(getleveljson.getId());
					
					//return ResponseEntity.ok(u1.getStress_level());
					return ResponseEntity.ok(new Return_User_Stress_leveljson(u1.getStress_level().toString(),"stress Level","success"));
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("cannot find user","fail"));
				}			}else {
				return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse(" invalid user input ","fail"));
		}
		
	}
	
	
	
	// (8) =================map councellers by user======================
		@RequestMapping(value="/user/mapCounceller",method=RequestMethod.POST)
		public ResponseEntity<?> mapcounceller(@RequestBody Mappingjson mappingjson,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			try {
				if(keycloakTokenValidater.Validate(token,secId,mappingjson.getUser_id()).equals("user")) {

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
					
					
									}else {
					return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
				}
			
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse("invalid user input","fail"));
			}
		}
	
	
		
		
		
		// (9)==================online chat with  counceller vs users==============================
		@RequestMapping(value="/chat",method=RequestMethod.POST)
		public ResponseEntity<?> chat(@RequestBody Chatjson chatjson,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			try {
				if(keycloakTokenValidater.Validate(token,secId,chatjson.getSender_id()).equals("user") || keycloakTokenValidater.Validate(token,secId,chatjson.getSender_id()).equals("counceller")) {
					if(allusersService.is_user_exist(chatjson.getSender_id()) && allusersService.is_user_exist(chatjson.getReceiver_id()) ) {
						AllUsers sender=allusersService.getuserfrom_id(Integer.parseInt(chatjson.getSender_id()));
						AllUsers receiver=allusersService.getuserfrom_id(Integer.parseInt(chatjson.getReceiver_id()));
						if(sender.getStatus().equals("enable") && receiver.getStatus().equals("enable")) {
							if(sender.getType().equals("user")) {
								//===========from user to counceller=============
								if(mapService.is_counceller_already_exist(chatjson.getSender_id(), chatjson.getReceiver_id())) {
									if(messagesService.update_msg_details(chatjson)) {
										Messages m=messagesService.get_last_instance_by_Receiver_id(chatjson.getReceiver_id());
										//System.out.println("tset2");
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
										Messages m=messagesService.get_last_instance_by_Receiver_id(chatjson.getReceiver_id());
										changesService.update_changed_table(new Changes("messages", m.getId(), "New Message", m.getReceiver(), "false", "messages",func.getCurrentdateTime()));

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
				
				}else {
					return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
				}
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponse("invalid user input","fail"));
			}
		
		}
			
		
		

		
		// (10) ======================view user details by counceller============================
		@RequestMapping(value="/PatientDetails",method=RequestMethod.POST)
		public ResponseEntity<?> getpatiantDetails(@RequestBody PatiantDetailsjson details,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			try {
				if(keycloakTokenValidater.Validate(token,secId,details.getCounceller_id()).equals("counceller")) {
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
					
					
				}else {
					return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
				}
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("invalid user input","fail"));
			}
				
			
		}		
		
		
		
		// (11) =============================controll conceller access by admin==============================
		@RequestMapping(value="/admin/accessControll", method=RequestMethod.POST)
		public ResponseEntity<?> accessControll(@RequestBody AccessControlJson para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			
			try {
				if(keycloakTokenValidater.Validate(token,secId,para.getAdmin_id()).equals("admin")) {
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
				}else {
					return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
				}
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("invalid user input","fail"));
			}
			
			
			//return ResponseEntity.ok("lol");
		}	
					
		
		
		// (12) ====================add music track by counceller=================================
		@RequestMapping(value="/addTrack",method=RequestMethod.POST)
		public ResponseEntity<?> addMusicTrack(@RequestBody AddTrackjson addTrackjson,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			try {
				if(keycloakTokenValidater.Validate(token,secId,addTrackjson.getCounceller_id()).equals("counceller")) {
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
				}else {
					return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
				}
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
			}
			
			
			
			
		}
			
		
	
		
		// (13) ===============controll music track by admin==========================
		@RequestMapping(value="/admin/controllTracks",params= {"admin_id","track_id","status"},method=RequestMethod.GET)
		public ResponseEntity<?> music_track_controll(@RequestParam("admin_id") String admin_id,@RequestParam("track_id") String track_id,@RequestParam("status") String status,@RequestHeader(value="id") String secId){
			
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
		
		
		
		
		// (14) ==================update account details==========================================
		@RequestMapping(value="/accountSetting",method=RequestMethod.POST)
		public ResponseEntity<?> updateAccountDetails(@RequestBody AccountSettingjson account,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			
			try {
				if(keycloakTokenValidater.Validate(token,secId,account.getId()).equals("admin") || keycloakTokenValidater.Validate(token,secId,account.getId()).equals("counceller") || keycloakTokenValidater.Validate(token,secId,account.getId()).equals("user")) {
					if(allusersService.is_user_exist(account.getId())) {
						if(allusersService.getuserfrom_id(Integer.parseInt(account.getId())).getStatus().equals("enable")) {
							AllUsers user=allusersService.getuserfrom_id(Integer.parseInt(account.getId()));
							boolean is_password_duplicate=false;
							boolean is_username_duplicate=false;
							
							if(account.getUsername().length()>0 || account.getPassword().length()>0) {
								
								if(account.getPassword().length()>0 && account.getUsername().length()>0 ){
									if(allusersService.check_is_duplicate_user(account.getUsername(), account.getPassword(),account.getId())) {
										is_password_duplicate=true;
										is_username_duplicate=true;
										System.out.println(account.getUsername()+ account.getPassword());
									}else {
										user.setUsername(account.getUsername());
										user.setPassword(func.string_encript(account.getPassword()));
										
										//update keycloak database
										keyCloakService.deleteUser(user.getKeycloakId());
										Signupjson data=new Signupjson();
										data.setType(user.getType());
										data.setUsername(account.getUsername());
										data.setPassword(account.getPassword());
										String kid=keyCloakService.createUserInKeyCloak(data);
										user.setKeycloakId(kid);
									}
								}else if(account.getUsername().length()>0) {
									if(allusersService.check_is_duplicate_user(account.getUsername(), func.string_decode(user.getPassword()),account.getId())) {
										is_username_duplicate=true;
									}else {
										user.setUsername(account.getUsername());
										//update keycloak database
										keyCloakService.deleteUser(user.getKeycloakId());
										Signupjson data=new Signupjson();
										data.setType(user.getType());
										data.setUsername(account.getUsername());
										data.setPassword(func.string_decode(user.getPassword()));
										String kid=keyCloakService.createUserInKeyCloak(data);
										user.setKeycloakId(kid);
									}
								}else if(account.getPassword().length()>0) {
									if(allusersService.check_is_duplicate_user(user.getUsername(), account.getPassword(),account.getId())) {
										is_password_duplicate=true;
									}else {
										user.setPassword(func.string_encript(account.getPassword()));
										//update keycloak database
										keyCloakService.resetPassword(func.string_encript(account.getPassword()), user.getKeycloakId());
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
											//uu.setProfile_pic_name(account.getProfile_pic_name()+account.getId());
										}if(account.getJob().length()>0) {
											uu.setJob(account.getJob());
										}if(account.getLatitude().length()>0) {
											uu.setLatitude(Double.parseDouble(account.getLatitude()));
										}if(account.getLongitude().length()>0) {
											uu.setLongitude(Double.parseDouble(account.getLongitude()));
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
										}if(account.getLatitude().length()>0) {
											cc.setLatitude(Double.parseDouble(account.getLatitude()));
										}if(account.getLongitude().length()>0) {
											cc.setLongitude(Double.parseDouble(account.getLongitude()));
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
				}else {
					return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
				}
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
			}
			
			
			
			
		}
		
		
		
		

		// (15)=====================add admin_notification by admin==========================
			@RequestMapping(value="/admin/addNotification",method=RequestMethod.POST)
			public ResponseEntity<?> add_admin_notification(@RequestBody AdminNotificationjson notice,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				
				try {
					if(keycloakTokenValidater.Validate(token,secId,notice.getAdmin_id()).equals("admin")) {
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
							
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
				}
				
			}
		
		
		//  (16)============controlling notice by admin==========================
			@RequestMapping(value="/admin/ControllNotice",method=RequestMethod.POST)
			public ResponseEntity<?> Controll_notice(@RequestBody AdminNotificationjson noty,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				
				try {
					if(keycloakTokenValidater.Validate(token,secId,noty.getAdmin_id()).equals("admin")) {
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
						
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
					
				}
				
				
				
			}
		
			// (17) ================send bookinng request by patient to counceller===============================
				@RequestMapping(value="/user/Request",method=RequestMethod.POST)
				public ResponseEntity<?> send_request_to_counceller(@RequestBody BookingRequestJson req,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
							try {
								if(keycloakTokenValidater.Validate(token,secId,req.getUser_id()).equals("user")) {
									if(allusersService.is_user_exist(req.getUser_id())) {
										if(allusersService.is_user_exist(req.getCounceller_id())) {
											AllUsers u=allusersService.getuserfrom_id(Integer.parseInt(req.getUser_id()));
											AllUsers c=allusersService.getuserfrom_id(Integer.parseInt(req.getCounceller_id()));
											if(u.getType().equals("user") && u.getStatus().equals("enable") && c.getType().equals("counceller") && c.getStatus().equals("enable")) {
												//if(mapService.is_counceller_already_exist(req.getUser_id(), req.getCounceller_id())) {
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
											//	}else {
											//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Initialy you want to map this counceller","fail"));
											//	}
											}else {
												return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user or counceller","fail"));
											}
										}else {
											return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Connot find Counceller","fail"));
										}
									}else {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Connot find user","fail"));
									}
									
								}else {
									return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
								}
								
							} catch (Exception e) {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));

							}		
							
							
				}
				
				
				
				
				
		//(18) ==========delete submited booking  request by user==========================================
		@RequestMapping(value="/user/deleterequest",method=RequestMethod.POST)				
		public ResponseEntity<?> delete_request(@RequestBody DeleteRequestJson param,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			try {
				if(keycloakTokenValidater.Validate(token,secId,param.getUser_id()).equals("user")) {
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
					
				}else {
					return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
				}
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
			}
					
		}
		
		
				
		//(19) ====================response to booking request by counceller===========================
		
			@RequestMapping(value="/counceller/Response",method=RequestMethod.POST)
			public ResponseEntity<?> Response_to_booking_request(@RequestBody ResponseJson para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getCounceller_id()).equals("counceller")) {
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
						
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid inputs","fail"));
				}
				
				
			}
			
					
				
				
	
		// (20) ================confirm counceller booking reply by patient==============================
			@RequestMapping(value="/user/confirm",method=RequestMethod.POST)
			public ResponseEntity<?> Confirm_counceller_booking_reply_by_user(@RequestBody Confirmjson para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getUser_id()).equals("user")) {
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
						
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
				}
					
				
			}
						
						
		// (21)=============================add tips by counceller============================================
			@RequestMapping(value="/counceller/AddTips",method=RequestMethod.POST)
			public ResponseEntity<?> add_tips(@RequestBody AddTipsjson para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getCounceller_id()).equals("counceller")) {
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
						
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid inputs","fail"));
				}
				
				
			}
						
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
			
					
								
			
	
		//(23) get user,counceller,admin data by id.....
			@RequestMapping(value="/getData",method=RequestMethod.POST)
			public ResponseEntity<?> get_data(@RequestBody Getdatjson para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getId()).equals("counceller") || keycloakTokenValidater.Validate(token,secId,para.getId()).equals("user") || keycloakTokenValidater.Validate(token,secId,para.getId()).equals("admin")) {
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
						
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs", "fail"));
				}
				
				
			}
		
						
						
		//(24) get last number of  messages according to affected user================================
			@RequestMapping(value="/getMessages",method=RequestMethod.POST)
			public ResponseEntity<?> getMessages(@RequestBody GetMessagesJson para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getId()).equals("user") || keycloakTokenValidater.Validate(token,secId,para.getId()).equals("counceller")) {
						if(allusersService.is_user_exist(para.getId())) {
							AllUsers a=allusersService.getuserfrom_id(Integer.parseInt(para.getId()));
							if(a.getStatus().equals("enable")) {
								GetMessageOut out=new GetMessageOut();
								if(para.getNumberOfRecords().equals("all")) {
									List<Messages> mmm=(List<Messages>) messagesService.get_all_records_by_id(Long.parseLong(para.getId()));
									if(mmm.isEmpty()) {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("No Any related Messages", "false"));
									}else {
										out.setRes_status("true");
										out.setList(mmm);
										return ResponseEntity.ok(out);
									}
									
								}else {
									List<Messages> mm=(List<Messages>) messagesService.get_last_records_by_id(Long.parseLong(para.getId()), Integer.parseInt(para.getNumberOfRecords()));
									if(mm.isEmpty()) {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("No Any related Messages", "false"));
									}else {
										out.setRes_status("true");
										out.setList(mm);
										return ResponseEntity.ok(out);
									}
									
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Access Denid By Admin", "false"));
							}
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot Find user", "false"));
						}
						
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid User inputs", "false"));
				}
				
				
				
			}
						
						
		//(25) get all councellers ======================================
			@RequestMapping(value="/getAllCouncellers",method=RequestMethod.POST)
			public ResponseEntity<?> getAllCouncellers(@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token,secId,secId).equals("user")) {
						List<AllUsers> l=(List<AllUsers>) allusersService.get_all_councellers("enable");
						if(l.isEmpty()) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find any Counceller","fail"));
							
						}else {
							GetAllCouncellerOut out=new GetAllCouncellerOut();
							out.setRes_status("true");
							List<UserdetailsResponse> ll=new ArrayList<UserdetailsResponse>();
							
							Integer k=0;
							for(AllUsers c:l) {
								UserdetailsResponse data=new UserdetailsResponse();
								Counceller cc=councellerService.get_counceller_by_id(c.getId().toString());
								data.setAddress(c.getAddress());
								data.setAge(c.getAge().toString());
								data.setBirth_date(c.getBirth_date());
								data.setEmail(c.getEmail());
								data.setGender(c.getGender());
								data.setId(c.getId().toString());
								
								data.setLatitude(cc.getLatitude().toString());
								data.setLongitude(cc.getLongitude().toString());
								
								
								data.setName(c.getName());
								data.setPhone_number(c.getPhone_number());
								//System.out.println(data.getName());
								ll.add(data);
								
							}
							out.setList(ll);
							return ResponseEntity.ok(out);
						}
						
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot Return data because latitude and logitudes are NULL","fail"));
				}
				
			}
	
			
		//(26) change exist mapped Counceller by user
			@RequestMapping(value="/user/ChangeCounceller",method=RequestMethod.POST)
			public ResponseEntity<?> change_mapped_counceller(@RequestBody ChangeCouncellerjson para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getUserId()).equals("user")) {
						if(allusersService.is_user_exist(para.getUserId())) {
							//System.out.println(para.getUserId()+"\t"+para.getCurrentCouncellerId()+"\t"+para.getNewCouncellerId());
							if(allusersService.is_user_exist(para.getCurrentCouncellerId()) && allusersService.is_user_exist(para.getNewCouncellerId()) ) {
								AllUsers u=allusersService.getuserfrom_id(Integer.parseInt(para.getUserId()));
								AllUsers c=allusersService.getuserfrom_id(Integer.parseInt(para.getNewCouncellerId()));
								if(u.getType().equals("user") && c.getType().equals("counceller") && c.getStatus().equals("enable") ) {
									if(mapService.is_counceller_already_exist(para.getUserId(), para.getCurrentCouncellerId())) {
										if(!mapService.is_counceller_already_exist(para.getUserId(), para.getNewCouncellerId())) {
											Map m=mapService.getInstanceBy_councellerId_and_userId(para.getCurrentCouncellerId(), para.getUserId());
											m.setCouncellerId(Long.parseLong(para.getNewCouncellerId()));
											mapService.updateTableByInstance(m);
											changesService.update_changed_table(new Changes("map", m.getId(), "Counceller Was Mapped", Long.parseLong(para.getNewCouncellerId()), "false", "notification", func.getCurrentdateTime()));
											return ResponseEntity.ok(new JsonResponse("Counceller mapping Success", "true"));
										}else {
											return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Counceller is Allready Mapped", "fail"));
										}
									}else {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid Mapping", "fail"));
									}
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid User or Counceller", "fail"));
								}
							//	return ResponseEntity.ok("lol");
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Counceller is Not Exist", "fail"));
							}
							//return ResponseEntity.ok("lol");
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("User is Not Exist", "fail"));
						}
						
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid User Inputs", "fail"));
				}
				
				
				//return ResponseEntity.ok("lol");
			}

		
		//(27) get last number of msges acording to one cht session
			@RequestMapping(value="/getOneChat",method=RequestMethod.POST)
			public ResponseEntity<?> getOneChat(@RequestBody GetOneChatJson para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getId1()).equals("counceller") || keycloakTokenValidater.Validate(token,secId,para.getId1()).equals("user")) {
						if(allusersService.is_user_exist(para.getId1()) && allusersService.is_user_exist(para.getId2())) {
							AllUsers u1=allusersService.getuserfrom_id(Integer.parseInt(para.getId1()));
							AllUsers u2=allusersService.getuserfrom_id(Integer.parseInt(para.getId2()));
							if(u1.getStatus().equals("enable") && u2.getStatus().equals("enable")) {
								GetMessageOut out=new GetMessageOut();
								if(para.getNumberOfInstance().equals("all")) {
									List<Messages> mm=(List<Messages>) messagesService.get_all_records_by_two_ids(Long.parseLong(para.getId1()), Long.parseLong(para.getId2()));
									if(mm.isEmpty()) {
										return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find any related megs", "false"));
									}else {
										out.setRes_status("true");
										out.setList(mm);
										return ResponseEntity.ok(out);
									}
								}else {
									List<Messages> mmm=(List<Messages>) messagesService.get_last_records_by_two_ids(Long.parseLong(para.getId1()), Long.parseLong(para.getId2()), Integer.parseInt(para.getNumberOfInstance()));
									out.setRes_status("true");
									out.setList(mmm);
									return ResponseEntity.ok(out);
								}
								
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Access Denied by Admin", "false"));
							}
							
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find any user", "false"));
						}
						
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid USer inputs", "false"));
				}
				
			}
			
		
			
		//(28)==============get avalable mapped councellers by user============================
			@PostMapping("/getMappedCouncellers")
			public ResponseEntity<?> getAvalabaleMappedCouncellers(@RequestBody GetMappedCounceller para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getId()).equals("user")) {
						if(allusersService.is_user_exist(para.getId())) {
							if(allusersService.getuserfrom_id(Integer.parseInt(para.getId())).getType().equals("user")) {
								
								if(mapService.existByuser_id(para.getId())) {
									List<UserdetailsResponse> list=new ArrayList<>();
									
									for(Map m:mapService.getInstanceFromUserId(para.getId())) {
										if(allusersService.is_user_exist(m.getCouncellerId().toString())) {
											AllUsers c=allusersService.getuserfrom_id(Integer.parseInt(m.getCouncellerId().toString()));
											if(c.getStatus().equals("enable")) {
												Counceller cc=councellerService.get_counceller_by_id(c.getId().toString());
												UserdetailsResponse data=new UserdetailsResponse(c.getId().toString(), c.getName(), c.getAge().toString(), c.getAddress(), c.getBirth_date(), c.getEmail(), c.getGender(), c.getPhone_number(), cc.getLatitude().toString(), cc.getLongitude().toString(), "true", cc.getQualification(), cc.getCertificate());
												list.add(data);
											}
										}									
									}
									GetMappedCouncellers out=new GetMappedCouncellers();
									out.setRes_status("true");
									out.setCouncellers(list);
									return ResponseEntity.ok(out);
									
								}else {
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Not mapped Councellers in List","fail"));

								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user","fail"));
							}
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("cannot find any user","fail"));
						}
					
					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs","fail"));
				}
				
			}
			
			
			
			
		//(29)=================get StressLevelHistory according to user=======================
			@PostMapping("/user/LevelHistory")
			public ResponseEntity<?> getUserStressLevelHistory(@RequestBody IdOnly para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getId()).equals("user") || keycloakTokenValidater.Validate(token,secId,para.getId()).equals("counceller")) {
						if(allusersService.is_user_exist(para.getId())) {
							if(stressLevelHistoryService.Is_exist_by_userId(para.getId())) {
								List<StressLevelHistory> list=(List<StressLevelHistory>) stressLevelHistoryService.getInstancesByUserId(para.getId());
								LevelHistoryjson data=new LevelHistoryjson();
								data.setRes_status("true");
								data.setHistoryList(list);
								return ResponseEntity.ok(data);
								
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("No Avalable History", "fail"));
							}
							
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find Any user", "fail"));
						}					}else {
						return ResponseEntity.ok(new JsonResponse("Unauthorized","fail"));
					}
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs", "fail"));
				}
				
			}
			
			
		//(30)================logout =========================================================
			@PostMapping("/logout")
			public ResponseEntity<?> logout(@RequestBody IdOnly para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token,secId,para.getId()).equals("user") || keycloakTokenValidater.Validate(token,secId,para.getId()).equals("counceller") || keycloakTokenValidater.Validate(token,secId,para.getId()).equals("admin")) {
						if(allusersService.is_user_exist(para.getId())) {
							AllUsers u=allusersService.getuserfrom_id(Integer.parseInt(secId));
							u.setLogingStatus("false");
							allusersService.updateAlluserInstance(u);
							String kid=allusersService.getuserfrom_id(Integer.parseInt(para.getId())).getKeycloakId();
							keyCloakService.logoutUser(kid);
							return ResponseEntity.ok(new JsonResponse("logout success","success"));
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find user", "fail"));
						}
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Error", "fail"));
					}
					
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs", "fail"));
				}
				
				
			}
			
			
		//(31)==================check_login=========================================================
		@PostMapping("/is_loging")
		public ResponseEntity<?> is_loging(@RequestBody IdOnly para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			try {
				if(keycloakTokenValidater.Validate(token,secId,para.getId()).equals("user") || keycloakTokenValidater.Validate(token,secId,para.getId()).equals("counceller") || keycloakTokenValidater.Validate(token,secId,para.getId()).equals("admin")) {
					if(allusersService.getuserfrom_id(Integer.parseInt(para.getId())).getLogingStatus().equals("true")) {
						return ResponseEntity.ok(new JsonResponse("login success", "success"));
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("loging fail", "fail"));
					}
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("loging fail", "fail"));

				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid user inputs", "fail"));
			}
			
		}
		
			
		//(32) ==========================getTips=============================================================
			@PostMapping("/getTips")
			public ResponseEntity<?> getTips(@RequestBody GetTips para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
				try {
					if(keycloakTokenValidater.Validate(token, secId,para.getId()).equals("user") || keycloakTokenValidater.Validate(token, secId,para.getId()).equals("counceller")) {
						if(allusersService.is_user_exist(para.getId())) {
							AllUsers c=allusersService.getuserfrom_id(Integer.parseInt(para.getId()));
							if((c.getType().equals("counceller") || c.getType().equals("user")) && c.getStatus().equals("enable")) {
								List<Tips> list=new ArrayList<>();
								if(para.getNumberOfInstance().equals("all")) {
									for(Tips t:tipsService.getAllrecords()) {
										list.add(t);
									}
								}else{
									for(Tips t:tipsService.getlastRecords(Integer.parseInt(para.getNumberOfInstance()))) {
											list.add(t);
									}
								}
								GrtTipsOut output=new GrtTipsOut();
								if(list.size()>0) {
									output.setResponse("success");
									output.setRes_status("success");
									output.setData(list);
									return ResponseEntity.ok(output);
								}else {
									output.setResponse("No any Tips");
									output.setRes_status("fail");
									return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
								}
							}else {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Invalid Counceller", "fail"));
							}
						}else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot find counceller or user", "fail"));
						}
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("unAuthorized", "fail"));
					}
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("invalid user inputs", "fail"));
				}
				
			}
			
			
		
		//(33)=================uploading images====================================================				
		  @PostMapping("/uploadPicture")
		  public ResponseEntity<?> uploadPicture(@RequestParam("file") MultipartFile file,@RequestParam("id") String id,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			  
			try {
				if(keycloakTokenValidater.Validate(token, secId,id).equals("user") || keycloakTokenValidater.Validate(token, secId,id).equals("counceller")) {
					String imagename=file.getOriginalFilename();
					  String needle1=".jpg";
					  String needle2=".jpeg";
					  String newimagename="";
					  boolean status=false;
					  if(imagename.toLowerCase().indexOf(needle1) != -1) {
						  newimagename=imagename.split(".jpg")[0]+id+".jpg";
						  status=true;
					  }else if(imagename.toLowerCase().indexOf(needle2) != -1) {
						  newimagename=imagename.split(".jpeg")[0]+id+".jpeg";
						  status=true;
					  }else {
						  status=false;
					  }
					  
					  if(status && newimagename.length()>0) {
						  File convertfile=new File(imagepath+newimagename);
						  try {
							  AllUsers u=allusersService.getuserfrom_id(Integer.parseInt(id));
							  if(u.getType().equals("user")) {
									User user=userService.getUserby_id(id);
									if(user.getProfile_pic_name()==null) {
										//System.out.println("image is  null");
										convertfile.createNewFile();
										FileOutputStream out=new FileOutputStream(convertfile);
										out.write(file.getBytes());
										out.close();
										user.setProfile_pic_name(newimagename);
										userService.update_edited_user_details(user);
										return ResponseEntity.ok(new JsonResponse("image upload succes", "success"));
									}else {
										//System.out.println("image is not null");
										if(user.getProfile_pic_name().equals(newimagename)) {
											return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("you allready using this image", "fail"));
										}else {
											File convertfileOld=new File(imagepath+user.getProfile_pic_name());
											try {
												convertfileOld.delete();
											} catch (Exception e) {
												System.out.println(e);
											}
											
											convertfile.createNewFile();
											FileOutputStream out=new FileOutputStream(convertfile);
											out.write(file.getBytes());
											out.close();
											user.setProfile_pic_name(newimagename);
											userService.update_edited_user_details(user);
											return ResponseEntity.ok(new JsonResponse("image upload succes", "success"));
										}
										
									}

								}else if(u.getType().equals("counceller")) {
									Counceller cou=councellerService.get_counceller_by_id(id);
									
									if(cou.getProfile_pic_name()==null) {
										//System.out.println("image is  null");
										convertfile.createNewFile();
										FileOutputStream out=new FileOutputStream(convertfile);
										out.write(file.getBytes());
										out.close();
										cou.setProfile_pic_name(newimagename);
										councellerService.update_Edited_counceller_details(cou);
										return ResponseEntity.ok(new JsonResponse("image upload succes", "success"));
									}else {
										System.out.println("image is not null");
										if(cou.getProfile_pic_name().equals(newimagename)) {
											return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("you allready using this image", "fail"));
										}else {
											File convertfileOld=new File(imagepath+cou.getProfile_pic_name());
											try {
												convertfileOld.delete();
											} catch (Exception e) {
												System.out.println(e);
											}
											
											
											convertfile.createNewFile();
											FileOutputStream out=new FileOutputStream(convertfile);
											out.write(file.getBytes());
											out.close();
											cou.setProfile_pic_name(newimagename);
											councellerService.update_Edited_counceller_details(cou);
											return ResponseEntity.ok(new JsonResponse("image upload succes", "success"));
										}
										
									}
									
								}
							  
								
								
								
								
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Uploading success", "success"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Uploading fail", "fail"));

							}
					  }else {
						  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Uploadinf fail", "fail"));
					  }
					  
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Unauthorized", "fail"));
				}				  
				  
			} catch (Exception e) {
				  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot process", "fail"));
			}
			  
			  
			
		  }
		
			
		//(34) ==========================download images====================================
		  @PostMapping("/getImage")
		  public ResponseEntity<?> getImage(@RequestBody IdOnly para,@RequestHeader(value="Authorization") String token,@RequestHeader(value="id") String secId){
			 try {
				if(keycloakTokenValidater.Validate(token, secId,para.getId()).equals("user") || keycloakTokenValidater.Validate(token, secId,para.getId()).equals("counceller")) {
					
					 String id=para.getId();
					 if(allusersService.is_user_exist(id)) {
						 AllUsers all=allusersService.getuserfrom_id(Integer.parseInt(id));
						 String image="";
						 if(all.getType().equals("user")) {
							 User u=userService.getUserby_id(id);
							 if(u.getProfile_pic_name()==null) {
								 if(all.getGender().equals("male")) {
									 image="default1.jpg";
								 }else {
									 image="default2.jpg";
								 }
								
							 }else {
								 image=u.getProfile_pic_name();
							 }
						 }else if(all.getType().equals("counceller")) {
							 Counceller c=councellerService.get_counceller_by_id(id);
							 if(c.getProfile_pic_name()==null) {
								 if(all.getGender().equals("male")) {
									 image="default1.jpg";
								 }else {
									 image="default2.jpg";
								 }
							 }else {
								 image=c.getProfile_pic_name();
							 }
						 }
						 
						try {
							  File file = new File(imagepath+image);
							  InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
							  return ResponseEntity.ok()
							            .header(HttpHeaders.CONTENT_DISPOSITION,
							                  "attachment;filename=" + file.getName())
							            .contentType(MediaType.IMAGE_JPEG).contentLength(file.length())
							            .body(resource);
						} catch (Exception e) {
							System.out.println(e);
							 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Error1", "fail"));
						}
						 
					 }else {
						 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("cannot find user", "fail"));
					 }
				}else {
					  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Unauthorized", "fail"));
				}

			} catch (Exception e) {
				  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Cannot process", "fail"));
			} 
			 
		  }
				
		
			
			
	
		  
		  
		  
		  
		  
						
//*****************************************************************************************	***********
		
			
  					
						
	
		 
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
	/*//=================get_all_councellerIds========================================
	@RequestMapping(value="user/getAllCouncellers",method=RequestMethod.POST)
	public ResponseEntity<?> getAllCouncellers(){
		
		return ResponseEntity.ok(allusersService.get_all_councellers("enable"));
	}*/
	
	
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
	
	
	
	
	

