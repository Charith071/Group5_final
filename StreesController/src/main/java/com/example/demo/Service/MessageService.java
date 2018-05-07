package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MessageDao;

@Service
public class MessageService {
	@Autowired
	private MessageDao messageDao;
}
