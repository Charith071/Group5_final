package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ConsulterDao;
import com.example.demo.entity.Consulter;
import com.example.demo.entity.User;
import com.example.demo.function.CommonFunction;

@Service
public class ConsulterService {
	@Autowired
	private ConsulterDao consulterDao;
	private CommonFunction commonFunction=new CommonFunction();
	
	public String saveuser(HttpServletRequest req,String id){
		Consulter c1=new Consulter();
		c1.setId(Integer.parseInt(id));
		c1.setAddress(req.getParameter("address"));
		c1.setCertificate(req.getParameter("certificate"));
		c1.setGender(req.getParameter("gender"));
		c1.setGpslocation(req.getParameter("gpslocation"));
		c1.setName(req.getParameter("name"));
		c1.setPassword(req.getParameter("password"));
		c1.setPhone_number(Integer.parseInt(req.getParameter("phone_number")));
		c1.setProfile_pic_image(req.getParameter("profile_pic_name"));
		c1.setQulification(req.getParameter("qulification"));
		c1.setUsername(req.getParameter("username"));
		c1.setAge(commonFunction.cal_age(req.getParameter("birth_date")));
		c1.setCreate_date(commonFunction.getCurrentdate());
		
		consulterDao.save(c1);
		return "Consulter creation is success!!";
	}
	
	
	public Consulter getuserfrom_username_password(HttpServletRequest req){
		Consulter user3=consulterDao.findByLogin(req.getParameter("username"), req.getParameter("password"));
		return user3;
	}


	public void delete_by_id(HttpServletRequest req) {
		if(consulterDao.existsById(Integer.parseInt(req.getParameter("id")))) {
			consulterDao.deleteById(Integer.parseInt(req.getParameter("id")));
		}
		
		
	}
	
	
	
	
}
