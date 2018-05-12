package com.example.demo.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Counceller;
import com.example.demo.dao.CouncellerDao;

@Service
public class CouncellerService {
	@Autowired
	private CouncellerDao councellerDao;
	
	//========for counceller registration===============
	public boolean update_table(HttpServletRequest req, Long id) {
		Counceller c1=new Counceller();
		c1.setCertificate(req.getParameter("certificate"));
		c1.setGps_location(Double.parseDouble(req.getParameter("gps")));
		c1.setId(id);
		c1.setProfile_pic_name(req.getParameter("profile_pic_name")+id.toString());
		c1.setQualification(req.getParameter("qualification"));
		councellerDao.save(c1);
		return true;
	}
	
	
	//============update counceller gps location=====================
	public boolean update_gps_location(HttpServletRequest req){
		if(councellerDao.existsById(Long.parseLong(req.getParameter("id")))) {
			Counceller c=councellerDao.findByIds(Integer.parseInt(req.getParameter("id")));
			c.setGps_location(Double.parseDouble(req.getParameter("gps")));
			councellerDao.save(c);
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	//==============chnage Counceller profile picture ===============================
		public boolean change_profile(HttpServletRequest req) {
			if(councellerDao.existsById(Long.parseLong(req.getParameter("id")))) {
				Counceller c2=councellerDao.findByIds(Integer.parseInt(req.getParameter("id")));
				c2.setProfile_pic_name(req.getParameter("profile_pic_name")+req.getParameter("id"));
				councellerDao.save(c2);
				
				return true;
			}else {
				return false;
			}
		}
	
	
	
	
}
