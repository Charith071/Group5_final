package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Consulter {
	@Id
	private Integer id;
	private String name;
	private Integer age;
	private String gender;
	private String address;
	private Integer phone_number;
	private String username;
	private String password;
	private String gpslocation;
	private String qulification;
	private String certificate;
	private String profile_pic_image;
	private String create_date;
	private String type="consulter";
	
	
	
	
	public String getType() {
		return type;
	}
	
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGpslocation() {
		return gpslocation;
	}
	public void setGpslocation(String gpslocation) {
		this.gpslocation = gpslocation;
	}
	public String getQulification() {
		return qulification;
	}
	public void setQulification(String qulification) {
		this.qulification = qulification;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getProfile_pic_image() {
		return profile_pic_image;
	}
	public void setProfile_pic_image(String profile_pic_image) {
		this.profile_pic_image = profile_pic_image;
	}
	
	
}
