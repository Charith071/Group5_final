package com.example.demo.extra;

import lombok.Data;

@Data
public class UserdetailsResponse {

	private String id;
	private String name;
	private String age;
	private String address;
	private String birth_date;
	private String email;
	private String gender;
	private String phone_number;
	private String guadiant_phone_no;
	private String latitude;
	private String longitude;
	private String job;
	private String stress_level;
	
	private String res_status;
	private String qualification;
	private String certificate;
	public UserdetailsResponse(String id, String name, String age, String address, String birth_date, String email,
			String gender, String phone_number, String guadiant_phone_no, String latitude, String longitude, String job,
			String stress_level, String res_status) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.birth_date = birth_date;
		this.email = email;
		this.gender = gender;
		this.phone_number = phone_number;
		this.guadiant_phone_no = guadiant_phone_no;
		this.latitude = latitude;
		this.longitude = longitude;
		this.job = job;
		this.stress_level = stress_level;
		this.res_status = res_status;
	}
	public UserdetailsResponse(String id, String name, String age, String address, String birth_date, String email,
			String gender, String phone_number, String latitude, String longitude, String res_status,
			String qualification, String certificate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.birth_date = birth_date;
		this.email = email;
		this.gender = gender;
		this.phone_number = phone_number;
		this.latitude = latitude;
		this.longitude = longitude;
		this.res_status = res_status;
		this.qualification = qualification;
		this.certificate = certificate;
	}
	public UserdetailsResponse() {
		super();
	}
	
	

	
	
	
	
	
	
	
	
	
	
}
