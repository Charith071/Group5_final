package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.AllUsers;
import com.example.demo.Entity.Changes;
import com.example.demo.Service.AdminNotificationService;
import com.example.demo.Service.AllusersService;
import com.example.demo.Service.BookingDetailsService;
import com.example.demo.Service.BookingRequestService;
import com.example.demo.Service.ChangesService;
import com.example.demo.Service.CouncellerService;
import com.example.demo.Service.KeyCloakService;
import com.example.demo.Service.MapService;
import com.example.demo.Service.MessagesService;
import com.example.demo.Service.MusicTrackService;
import com.example.demo.Service.StressLevelHistoryService;
import com.example.demo.Service.TableInformationService;
import com.example.demo.Service.TipsService;
import com.example.demo.Service.UserService;
import com.example.demo.commonFunction.CommnFunction;
import com.example.demo.extra.JsonResponse;
import com.example.demo.extra.Loginjson;
import com.example.demo.extra.Loginout;
import com.example.demo.extra.Signupjson;

@RestController
@RequestMapping("/org")
public class Origine {
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
	private KeyCloakService keyClockService;
	
	
	private CommnFunction func=new CommnFunction();
	private JsonResponse jsonResponse=new JsonResponse();
	
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
							String kid=keyClockService.createUserInKeyCloak(signupjson);
							alluser.setKeycloakId(kid);
							allusersService.updateAlluserInstance(alluser);
							if(alluser.getType().equals("user")) {
								//also update stress level and history								
									//keyClockService.createUserInKeyCloak(signupjson);
									userService.update_table(signupjson,alluser.getId());
									return ResponseEntity.ok(new JsonResponse(alluser.getId().toString(),"success"));
								
								
							}else if(alluser.getType().equals("counceller")) {
							//	keyClockService.createUserInKeyCloak(signupjson);
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
						 String token="";
						 token=keyClockService.getToken(lj).split(":")[1].split(",")[0];
						 token=token.substring(1, token.length()-1);
						
						Loginout out=new Loginout("login success", "success", u3.getId().toString(), u3.getName(), u3.getAge().toString(), u3.getAddress(), u3.getBirth_date(), u3.getCreate_date(), u3.getEmail(), u3.getGender(), u3.getPhone_number(), u3.getType(), token);
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
	
	
	
}
