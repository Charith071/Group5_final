package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private Integer id;
	private String name;
	private Integer age;
	private String username;
	private String password;	
	private String gender;
	private String email;
	private String gpslocation;
	private String address;
	private String birthdate;
	private Integer phone_number;
	private String job;
	private Integer gardiant_phone_number;
	private Double stress_level;
	private String create_date;
	private String profile_pic_name;
	private String type="user";
	
	
	
	
	public String getType() {
		return type;
	}
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfile_pic_name() {
		return profile_pic_name;
	}

	public void setProfile_pic_name(String profile_pic_name) {
		this.profile_pic_name = profile_pic_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public Double getStress_level() {
		return stress_level;
	}

	public void setStress_level(Double stress_level) {
		this.stress_level = stress_level;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getGpslocation() {
		return gpslocation;
	}

	public void setGpslocation(String gpslocation) {
		this.gpslocation = gpslocation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getGardiant_phone_number() {
		return gardiant_phone_number;
	}

	public void setGardiant_phone_number(Integer gardiant_phone_number) {
		this.gardiant_phone_number = gardiant_phone_number;
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
	
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
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
	
	

}
