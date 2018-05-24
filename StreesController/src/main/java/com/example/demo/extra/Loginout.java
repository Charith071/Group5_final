package com.example.demo.extra;

public class Loginout {

	private String response;
	private String res_status;
	
	private String id;
	private String name;
	private String age;
	private String address;
	private String birth_date;
	private String create_date;
	private String email;
	private String gender;
	private String phone_number;
	private String type;
	private String session_token;
	
	
	
	public Loginout(String response, String res_status, String id, String name, String age, String address,
			String birth_date, String create_date, String email, String gender, String phone_number, String type,
			String session_token) {
		super();
		this.response = response;
		this.res_status = res_status;
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.birth_date = birth_date;
		this.create_date = create_date;
		this.email = email;
		this.gender = gender;
		this.phone_number = phone_number;
		this.type = type;
		this.session_token = session_token;
	}
	public Loginout() {
		super();
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getRes_status() {
		return res_status;
	}
	public void setRes_status(String res_status) {
		this.res_status = res_status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSession_token() {
		return session_token;
	}
	public void setSession_token(String session_token) {
		this.session_token = session_token;
	}
	
	
	
	
	
	
}
