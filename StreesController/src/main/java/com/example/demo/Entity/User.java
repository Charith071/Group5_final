package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id //(should be foreign key of alluser.id)
	private Long id;
	private String guadiant_phone_no;
	private Double latitude;
	private Double longitude;
	private String job;
	private Float stress_level;
	private String profile_pic_name;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGuadiant_phone_no() {
		return guadiant_phone_no;
	}
	public void setGuadiant_phone_no(String guadiant_phone_no) {
		this.guadiant_phone_no = guadiant_phone_no;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Float getStress_level() {
		return stress_level;
	}
	public void setStress_level(Float stress_level) {
		this.stress_level = stress_level;
	}
	public String getProfile_pic_name() {
		return profile_pic_name;
	}
	public void setProfile_pic_name(String profile_pic_name) {
		this.profile_pic_name = profile_pic_name;
	}
	
	
	
	
		
}
