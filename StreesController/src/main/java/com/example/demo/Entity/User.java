package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id //(should be foreign key of alluser.id)
	private Long id;
	private Integer guadiant_phone_no;
	private Double gps_location;
	private String job;
	private Float stress_level;
	private String profile_pic_name;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getStress_level() {
		return stress_level;
	}
	public void setStress_level(Float stress_level) {
		this.stress_level = stress_level;
	}
	public Integer getGuadiant_phone_no() {
		return guadiant_phone_no;
	}
	public void setGuadiant_phone_no(Integer guadiant_phone_no) {
		this.guadiant_phone_no = guadiant_phone_no;
	}
	public Double getGps_location() {
		return gps_location;
	}
	public void setGps_location(Double gps_location) {
		this.gps_location = gps_location;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	public String getProfile_pic_name() {
		return profile_pic_name;
	}
	public void setProfile_pic_name(String profile_pic_name) {
		this.profile_pic_name = profile_pic_name;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", guadiant_phone_no=" + guadiant_phone_no + ", gps_location=" + gps_location
				+ ", job=" + job + ", stress_level=" + stress_level + ", profile_pic_name=" + profile_pic_name + "]";
	}
		
}
