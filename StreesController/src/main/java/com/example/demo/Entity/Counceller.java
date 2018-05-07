package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Counceller {
	@Id//(should be foreign key of alluser.id)
	private Long id;
	private Double gps_location;
	private String certificate;
	private String qualification;
	private String profile_pic_name;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getGps_location() {
		return gps_location;
	}
	public void setGps_location(Double gps_location) {
		this.gps_location = gps_location;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getProfile_pic_name() {
		return profile_pic_name;
	}
	public void setProfile_pic_name(String profile_pic_name) {
		this.profile_pic_name = profile_pic_name;
	}
	
	
}
