package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminNotificationDao;

@Service
public class AdminNotificationService {
	@Autowired
	private AdminNotificationDao adminNotificationDao;
}
