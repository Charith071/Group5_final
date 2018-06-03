package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Messages;
import com.example.demo.dao.MessagesDao;
import com.example.demo.extra.Chatjson;

@Service
public class MessagesService {
	@Autowired
	private MessagesDao messagesDao;
	
	
	//============update sending and receiving msg=============
	public boolean update_msg_details(Chatjson chatjson) {
		Messages msg=new Messages();
		msg.setDate_time(chatjson.getDate_time());
		msg.setMsg(chatjson.getMsg());
		msg.setReceiver(Long.parseLong(chatjson.getReceiver_id()));
		msg.setSender(Long.parseLong(chatjson.getSender_id()));
		messagesDao.save(msg);
		return true;
	}
	
	
	//============= realtime===================================
		public String getLastUptadeTime() {
			return messagesDao.getLastUptadeTime();
		}
	
	//===============get number of rows========================
	public Long get_number_of_rows() {
		return messagesDao.count();
	}
	
	//====================get last recode=======================
	public Messages getLastRecorde() {
		return messagesDao.getLastRecord();
	}
	
	//==============get last instance by Receiver id
	public Messages get_last_instance_by_Receiver_id(String id) {
		return messagesDao.getLastRecord_by_receveir_id(Long.parseLong(id));
	}
	
	
}
