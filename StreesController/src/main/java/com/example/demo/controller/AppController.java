package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public ResponseEntity<?> lol(){
		return ResponseEntity.ok("postmethos");
	}
}
