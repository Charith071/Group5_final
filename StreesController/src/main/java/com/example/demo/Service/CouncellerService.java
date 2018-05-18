package com.example.demo.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Counceller;
import com.example.demo.dao.CouncellerDao;
import com.example.demo.extra.Gpsjson;
import com.example.demo.extra.Profilepicjson;
import com.example.demo.extra.Signupjson;

@Service
public class CouncellerService {
	@Autowired
	private CouncellerDao councellerDao;
	
	//========for counceller registration===============
	public boolean update_table(Signupjson signupjson, Long id) {
		Counceller c1=new Counceller();
		c1.setCertificate(signupjson.getCertificate());
		c1.setGps_location(Double.parseDouble(signupjson.getGps()));
		c1.setId(id);
		c1.setProfile_pic_name(signupjson.getProfile_pic_name()+id.toString());
		c1.setQualification(signupjson.getQualification());
		councellerDao.save(c1);
		return true;
	}
	
	
	//============update counceller gps location=====================
	public boolean update_gps_location(Gpsjson gpsjson1){
		if(councellerDao.existsById(Long.parseLong(gpsjson1.getId()))) {
			Counceller c=councellerDao.findByIds(Integer.parseInt(gpsjson1.getId()));
			c.setGps_location(Double.parseDouble(gpsjson1.getGps()));
			councellerDao.save(c);
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	//==============chnage Counceller profile picture ===============================
		public boolean change_profile(Profilepicjson profilepicjson) {
			if(councellerDao.existsById(Long.parseLong(profilepicjson.getId()))) {
				Counceller c2=councellerDao.findByIds(Integer.parseInt(profilepicjson.getId()));
				c2.setProfile_pic_name(profilepicjson.getProfile_pic_name()+profilepicjson.getId());
				councellerDao.save(c2);
				
				return true;
			}else {
				return false;
			}
		}
	
	
	//=============return counceller instance by id============================
		public Counceller get_counceller_by_id(String id) {
			return councellerDao.findByIds(Integer.parseInt(id));
		}
		
	//==============update edited counceller details=================
		public boolean update_Edited_counceller_details(Counceller cc) {
			councellerDao.save(cc);
			return true;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
