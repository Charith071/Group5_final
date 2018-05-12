package com.example.demo.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MapDao;

@Service
public class MapService {
	@Autowired
	private MapDao mapDao;
	
	//===========exist by userid=============
	public boolean existByuser_id(HttpServletRequest req) {
		return mapDao.existsByUserId(Long.parseLong(req.getParameter("user_id")));
	}
	
	
	//=================count number of instance by user_id==================
	public Long numberOfinstance_by_userId(HttpServletRequest req) {
		return mapDao.countByUserId(Long.parseLong(req.getParameter("user_id")));
	}
	
	//==============count number of instance by counceller_id===========
	public Long numberOfinstance_by_councellerId(HttpServletRequest req) {
		return mapDao.countByCouncellerId(Long.parseLong(req.getParameter("counceller_id")));
	}
	
	
}
