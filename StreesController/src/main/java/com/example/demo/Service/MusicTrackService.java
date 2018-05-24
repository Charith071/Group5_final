package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.MusicTrack;
import com.example.demo.commonFunction.CommnFunction;
import com.example.demo.dao.MusicTrackDao;
import com.example.demo.extra.AddTrackjson;

@Service
public class MusicTrackService {
	@Autowired
	private MusicTrackDao musicTrackDao;
	private CommnFunction func=new CommnFunction();
	
	//===================update music track=======================
	public boolean updateMusicTracktable(AddTrackjson addTrackjson) {
		MusicTrack mt=new MusicTrack();
		mt.setCounceller_id(Long.parseLong(addTrackjson.getCounceller_id()));
		mt.setDate_time(func.getCurrentdateTime());
		mt.setDescription(addTrackjson.getDescription());
		mt.setName(addTrackjson.getName());
		mt.setStatus("enable");
		mt.setMax_stress_level(Double.parseDouble(addTrackjson.getMax_stress_level()));
		mt.setMin_stress_level(Double.parseDouble(addTrackjson.getMin_stress_level()));
		musicTrackDao.save(mt);
		
		return true;
	}
	
	//=====================check duplicate name is exist================
	public boolean is_duplicate_name_exist(String name) {
		return musicTrackDao.existsByName(name);
	}
	
	//============cheack exist by id==============================
	public boolean is_exist_by_id(Long id) {
		return musicTrackDao.existsById(id);
	}
	
	//=====get instance by id=================
	public MusicTrack get_instance_by_id(Integer id) {
		return musicTrackDao.findByIds(id);
	}
	
	//============delete music track by id==================
	public boolean delete_track_by_id(Long id) {
		musicTrackDao.deleteById(id);
		return true;
	}
	
	
	//===============update insatnce=============
	public boolean update_instance(MusicTrack track) {
		musicTrackDao.save(track);
		return true;
	}
	
	//============= realtime====================
		public String getLastUptadeTime() {
			return musicTrackDao.getLastUptadeTime();
		}
		
	//===============get number of rows======================
	public Long get_number_of_rows() {
		return musicTrackDao.count();
	}
	
	//====================get last recode=======================
	public MusicTrack getLastRecorde() {
		return musicTrackDao.getLastRecord();
	}
	
	
	
	
	
	
	
	
	
	
	
	
 }
